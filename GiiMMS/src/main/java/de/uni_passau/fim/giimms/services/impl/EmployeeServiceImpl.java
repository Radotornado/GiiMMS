package de.uni_passau.fim.giimms.services.impl;

import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.repositories.EmployeeRepository;
import de.uni_passau.fim.giimms.repositories.RoleRepository;
import de.uni_passau.fim.giimms.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Implementation of the EmployeeService. Saves and searches for the employees,
 * saved in the database. Also handles updating the admin for the list
 * of all employees.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final Employee employee) {
        employee.setPassword(passEncoder.encode(employee.getPassword()));
        employee.setRoles(new HashSet<>(roleRepository.findAll()));
        employeeRepository.save(employee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(final List<Employee> employees) {
        for (Employee employee : employees) {
            save(employee);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee findByUsername(final String username) {
        return employeeRepository.findByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee findById(final long id) {
        return employeeRepository.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(final String username) {
        employeeRepository.delete(employeeRepository.findByUsername(username));
    }

    @Override
    public void changePassword(Employee employee, boolean b, String password) {
        employee.setFirstLogin(b ,password);
    }

}
