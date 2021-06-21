package de.uni_passau.fim.giimms.repositories;

import de.uni_passau.fim.giimms.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
