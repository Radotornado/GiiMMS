package de.uni_passau.fim.giimms.util;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String username) {
        super(username);
    }
}
