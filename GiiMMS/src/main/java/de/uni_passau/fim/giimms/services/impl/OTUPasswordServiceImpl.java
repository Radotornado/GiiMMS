package de.uni_passau.fim.giimms.services.impl;

import de.uni_passau.fim.giimms.model.OTUPassword;
import de.uni_passau.fim.giimms.repositories.OTUPasswordRepositories;
import de.uni_passau.fim.giimms.services.EmployeeService;
import de.uni_passau.fim.giimms.services.OTUPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OTUPasswordServiceImpl implements OTUPasswordService {
    @Autowired
    OTUPasswordRepositories otuPasswordRepositories;

    @Autowired
    EmployeeService employeeService;

    /**
     * Uses the OTUPassword and will delete it
     * @param username the username to search for
     * @return wether or not the User is expired
     */
    @Override
    public boolean useOTUPassword(String username) {
        OTUPassword otuPassword = otuPasswordRepositories.findByUsername(username);
        if(new Date().compareTo(otuPassword.getExpires()) > 0){
            otuPasswordRepositories.delete(otuPassword);
            return true;
        }
        employeeService.userExpired(username);
        return false;
    }

    /**
     * saves an instance of an otuPassword to the repository
     * @param otuPassword: given otuPassword to save
     */
    @Override
    public void save(OTUPassword otuPassword) {
        otuPasswordRepositories.save(otuPassword);
    }
}
