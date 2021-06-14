package de.uni_passau.fim.giimms.repositories;

import de.uni_passau.fim.giimms.util.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
    Employee findById(long id);
    Optional<Employee> findByUsername(String username);
}