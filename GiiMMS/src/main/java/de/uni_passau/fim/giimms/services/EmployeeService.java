package de.uni_passau.fim.giimms.services;

import de.uni_passau.fim.giimms.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Employee service is responsible for saving, finding and updating
 * Employees (and admins).
 */
@Service
public interface EmployeeService {

    /**
     * Encodes the password, sets the roles and saves an Employee
     * in the repository.
     *
     * @param employee The Employee to save.
     */
    void save(Employee employee);

    /**
     * Encodes the password, sets the roles and saves a list of Employees
     * in the repository.
     *
     * @param employees The list of employees to save.
     */
    void saveAll(List<Employee> employees);

    /**
     * Searches through the database for an Employee with a given username.
     *
     * @param username The username to search for.
     * @return The Employee, or if there is no Employee with this username
     * {@code null}.
     */
    Employee findByUsername(String username);

    /**
     * Searches through the database for an Employee with a given id.
     *
     * @param id The username to search for.
     * @return The Employee, or if there is no Employee with this id
     * {@code null}.
     */
    Employee findById(long id);

    /**
     * Deletes an Employee with a given username, if such Employee exists
     *
     * @param username The username to search for.
     */
    void delete(String username);

    void changePassword(Employee employee, boolean b, String password);

    void userExpired(String username);

    /**
     * Changes the username of an Employee.
     */
    void changeUsername(Employee employee, String newUsername);
}
