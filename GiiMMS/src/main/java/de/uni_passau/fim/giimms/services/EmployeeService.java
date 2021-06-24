package de.uni_passau.fim.giimms.services;

import de.uni_passau.fim.giimms.model.Employee;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    void save(Employee employee);

    Employee findByUsername(String username);
}
