package de.uni_passau.fim.giimms.repositories;

import de.uni_passau.fim.giimms.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The employee repository extends JpaRepository in order to have access to the
 * included methods and adds additionaly can find an Employee by username.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Adds possibility to find an employee by username.
     *
     * @param username The username to search for.
     * @return The employee, if present or {@code null} if not.
     */
    Employee findByUsername(final String username);
}