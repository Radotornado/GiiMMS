package de.uni_passau.fim.giimms.services;

import de.uni_passau.fim.giimms.model.OTUPassword;
import de.uni_passau.fim.giimms.repositories.OTUPasswordRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface OTUPasswordService {

    /**
     * Deletes an entity of OTUPassword on Login
     */
    boolean useOTUPassword(String username);

    void save(OTUPassword otuPassword);
}
