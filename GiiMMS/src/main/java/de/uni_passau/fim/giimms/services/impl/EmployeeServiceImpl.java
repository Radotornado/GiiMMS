package de.uni_passau.fim.giimms.services.impl;

import de.uni_passau.fim.giimms.model.Admin;
import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.repositories.EmployeeRepository;
import de.uni_passau.fim.giimms.repositories.RoleRepository;
import de.uni_passau.fim.giimms.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public void save(Employee employee) {
        employee.setPassword(passEncoder.encode(employee.getPassword()));
        employee.setRoles(new HashSet<>(roleRepository.findAll()));
        employeeRepository.save(employee);
    }

    @Override
    public void saveAll(List<Employee> employees) {
        for (Employee employee : employees) {
            employeeRepository.save(employee);
        }
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public Employee findById(long id) {
        return employeeRepository.getById(id);
    }

    @Override
    public void delete(String username) {
        employeeRepository.delete(employeeRepository.findByUsername(username));
    }

    @Override
    public void update(Admin admin) {
        List<Employee> employees = employeeRepository.findAll();
        employees.removeIf(Employee::isAdmin);
        admin.setEmployees(employees);
        admin.setPassword(passEncoder.encode(admin.getPassword()));
        admin.setRoles(new HashSet<>(roleRepository.findAll()));
        employeeRepository.save(admin);
    }
}
