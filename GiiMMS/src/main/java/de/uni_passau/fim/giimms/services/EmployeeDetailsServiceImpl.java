package de.uni_passau.fim.giimms.services;

import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.model.Role;
import de.uni_passau.fim.giimms.repositories.EmployeeRepository;
import de.uni_passau.fim.giimms.util.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee " + employee + " not found.");
        }
        Set<GrantedAuthority> grantedAuthority = new HashSet<>();
        for (Role role : employee.getRoles()) {
            grantedAuthority.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(employee.getUsername(), employee.getPassword(), grantedAuthority);
    }
}
