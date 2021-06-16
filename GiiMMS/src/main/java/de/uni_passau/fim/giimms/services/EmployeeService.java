package de.uni_passau.fim.giimms.services;

import de.uni_passau.fim.giimms.util.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService extends UserDetailsService {
    Employee save(Employee employee);
}
