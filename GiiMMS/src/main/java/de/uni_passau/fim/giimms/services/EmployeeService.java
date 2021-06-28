package de.uni_passau.fim.giimms.services;

import de.uni_passau.fim.giimms.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface EmployeeService {
    void save(Employee employee);
    void saveAll(Set<Employee> employees);
    Employee findByUsername(String username);
}
