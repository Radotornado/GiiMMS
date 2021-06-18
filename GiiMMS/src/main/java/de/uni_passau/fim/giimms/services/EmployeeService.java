package de.uni_passau.fim.giimms.services;

import de.uni_passau.fim.giimms.repositories.EmployeeRepository;
import de.uni_passau.fim.giimms.util.ConfirmationToken;
import de.uni_passau.fim.giimms.util.Employee;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final Optional<Employee> optionalEmployee = employeeRepository.findByEmail(email);

        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }
        else {
            throw new UsernameNotFoundException("User not found.");
        }
    }

    public void signUpUser(Employee employee) {

        final String encryptedPassword = bCryptPasswordEncoder.encode(employee.getPassword());

        employee.setPassword(encryptedPassword);

        final Employee createdUser = employeeRepository.save(employee);

        final ConfirmationToken confirmationToken = new ConfirmationToken(employee);

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        sendConfirmationMail(employee.getEmail(), confirmationToken.getConfirmationToken());

    }

    void sendConfirmationMail(String userMail, String token) {

        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userMail);
        mailMessage.setSubject("Mail Confirmation!");
        mailMessage.setFrom("<MAIL>");
        mailMessage.setText(
                "You are registered. Please click on the below link to activate your account." + "http://localhost:8080/addEmployee/confirm?token="
                        + token);

        emailSenderService.sendEmail(mailMessage);
    }


    public void confirmEmployee(ConfirmationToken confirmationToken) {

        final Employee employee = confirmationToken.getEmployee();

        employee.setEnabled(true);

        employeeRepository.save(employee);

        confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());

    }
}
