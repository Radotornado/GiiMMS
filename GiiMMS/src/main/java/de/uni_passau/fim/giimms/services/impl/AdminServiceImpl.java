package de.uni_passau.fim.giimms.services.impl;

import de.uni_passau.fim.giimms.model.Admin;
import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.repositories.EmployeeRepository;
import de.uni_passau.fim.giimms.repositories.RoleRepository;
import de.uni_passau.fim.giimms.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Updates or saves an Admin.
 */
@Service
public class AdminServiceImpl implements AdminService {

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
    public void update(final Admin admin) {
        // get a list of all employees and remove all admins from it
        List<Employee> employees = employeeRepository.findAll();
        employees.removeIf(Employee::getAdmin);
        admin.setEmployees(employees);
        admin.setPassword(passEncoder.encode(admin.getPassword()));
        admin.setRoles(new HashSet<>(roleRepository.findAll()));
        employeeRepository.save(admin);
    }
}
