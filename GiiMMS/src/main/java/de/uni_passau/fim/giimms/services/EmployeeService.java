package de.uni_passau.fim.giimms.services;

import de.uni_passau.fim.giimms.model.Admin;
import de.uni_passau.fim.giimms.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    void save(Employee employee);
    void saveAll(List<Employee> employees);
    Employee findByUsername(String username);
    Employee findById(long id);
    void delete(String username);
    void update(Admin admin);
}
