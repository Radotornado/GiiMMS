package de.uni_passau.fim.giimms.repositories;

import de.uni_passau.fim.giimms.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The role repository extends the JpaRepository with Role and Long (the id)
 * in order to have access to the findAll() method.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
