package de.uni_passau.fim.giimms.repositories;

import de.uni_passau.fim.giimms.util.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findById(long id);

    Employee findByUsername(String username);
}