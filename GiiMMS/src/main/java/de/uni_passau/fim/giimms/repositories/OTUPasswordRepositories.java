package de.uni_passau.fim.giimms.repositories;

import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.model.OTUPassword;
import de.uni_passau.fim.giimms.services.OTUPasswordService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTUPasswordRepositories extends JpaRepository<OTUPassword,
        Long> {

    /**
     * Adds possibility to find an OTUPassword by username.
     *
     * @param username The username to search for.
     * @return The employee, if present or {@code null} if not.
     */
    OTUPassword findByUsername(final String username);
}
