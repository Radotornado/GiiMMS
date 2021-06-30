package de.uni_passau.fim.giimms.services.impl;

import de.uni_passau.fim.giimms.services.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Implementation on SecurityService, where the main part behind authenticating
 * and auto login are situated.
 */
@Service
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
            return false;
        }
        return auth.isAuthenticated();
    }
}
