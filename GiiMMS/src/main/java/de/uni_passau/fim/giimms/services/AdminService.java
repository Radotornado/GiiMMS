package de.uni_passau.fim.giimms.services;

import de.uni_passau.fim.giimms.model.Admin;
import org.springframework.stereotype.Service;

/**
 * The Admin service is responsible for updating and saving an Admin.
 */
@Service
public interface AdminService {

    /**
     * Updates and sets an admin with the list of all employees.
     *
     * @param admin The admin to update or set.
     */
    void update(Admin admin);
}
