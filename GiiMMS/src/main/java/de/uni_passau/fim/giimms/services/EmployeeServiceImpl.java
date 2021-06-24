package de.uni_passau.fim.giimms.services;

import de.uni_passau.fim.giimms.GiiMmsApplication;
import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.repositories.EmployeeRepository;
import de.uni_passau.fim.giimms.repositories.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

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
        Logger log = LoggerFactory.getLogger(GiiMmsApplication.class);
        log.error(employee.toString() + " from save");
        employee.setPassword(passEncoder.encode(employee.getPassword()));
        employee.setRoles(new HashSet<>(roleRepository.findAll()));
        employeeRepository.save(employee);
    }

    @Override
    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }
}
