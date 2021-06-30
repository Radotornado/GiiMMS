package de.uni_passau.fim.giimms.util;

/**
 * Handles not finding an employee.
 */
public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String username) {
        super(username);
    }
}
