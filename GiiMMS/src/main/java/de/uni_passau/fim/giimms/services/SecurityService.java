package de.uni_passau.fim.giimms.services;

import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    boolean isAuthenticated();

    void autoLogin(String username, String password);
}

