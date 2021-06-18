package de.uni_passau.fim.giimms.controllers;

import de.uni_passau.fim.giimms.services.ConfirmationTokenService;
import de.uni_passau.fim.giimms.services.EmployeeService;
import de.uni_passau.fim.giimms.util.ConfirmationToken;
import de.uni_passau.fim.giimms.util.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    private final ConfirmationTokenService confirmationTokenService;

    @GetMapping("/")
    String signIn() {
        return "index";
    }

    @GetMapping("/addEmployee")
    String signUp(Model model) {
        model.addAttribute("employee");
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    String signUp(Employee employee, Model model) {

        model.addAttribute("employee");
        employeeService.signUpUser(employee);

        return "redirect:/addEmployee";
    }

    @GetMapping("/confirm")
    String confirmMail(@RequestParam("token") String token) {

        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);

        optionalConfirmationToken.ifPresent(employeeService::confirmEmployee);

        return "/sign-in";
    }

}
