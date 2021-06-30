package de.uni_passau.fim.giimms.services;

import org.springframework.stereotype.Service;

/**
 * The security service is responsible for checking if a user is authenticated.
 */
@Service
public interface SecurityService {

    /**
     * Checks if a user is authenticated.
     *
     * @return If the user authenticated.
     */
    boolean isAuthenticated();
}

