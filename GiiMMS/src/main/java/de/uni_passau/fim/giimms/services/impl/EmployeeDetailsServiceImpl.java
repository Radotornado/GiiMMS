package de.uni_passau.fim.giimms.services.impl;

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

/**
 * Implementation of the UserDetailsService interface from springframework.
 * Handles loading an Employee from username.
 */
@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     *  Finds the Employee by the given username (if present), grants it
     *  the needed authorities for spring security and returns a new
     *  {@code org.springframework.security.core.userdetails.User} object
     *  with the needed username, password and authority.
     *
     * @param username The username to search for.
     * @return A new User object with username, password and granted authority.
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee " + username + " not found.");
        }
        Set<GrantedAuthority> grantedAuthority = new HashSet<>();
        for (Role role : employee.getRoles()) {
            grantedAuthority.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(employee.getUsername(), employee.getPassword(), grantedAuthority);
    }
}
