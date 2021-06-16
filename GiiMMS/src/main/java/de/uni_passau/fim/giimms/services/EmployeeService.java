package de.uni_passau.fim.giimms.services;

import de.uni_passau.fim.giimms.util.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    Employee save(Employee employee);
}
