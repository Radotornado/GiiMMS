package de.uni_passau.fim.giimms.util;

import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.services.EmployeeService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validates if username and password are empty.
 */
@Component
public class EmployeeValidator implements Validator {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Determines if the EmployeeValidator validate instances
     * of a supplied class.
     *
     * @return If the class is possible to be validated.
     */
    @Override
    public boolean supports(@NotNull final Class<?> aClass) {
        return Employee.class.equals(aClass);
    }

    /**
     * Validates a given object and in case of validation errors,
     * registers those with the given Errors object.
     */
    @Override
    public void validate(@NotNull final Object o,
                         @NotNull final Errors errors) {
        // cast to Employee
        Employee employee = (Employee) o;

        // check if empty and needed length
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (employee.getUsername().length() < 6
                || employee.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        // is it unique?
        if (employeeService.findByUsername(employee.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        // check if empty and needed length
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (employee.getPassword().length() < 8
                || employee.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
    }
}
