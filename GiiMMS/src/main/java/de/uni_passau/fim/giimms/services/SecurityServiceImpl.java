package de.uni_passau.fim.giimms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger log = LoggerFactory.getLogger(SecurityServiceImpl.class);

    @Override
    public boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
            return false;
        }
        return auth.isAuthenticated();
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // I think the error was here ^ UserDetails cannot find by email
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(userDetails, password,
                userDetails.getAuthorities());
        authManager.authenticate(token);
        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
            log.info("Auto login + " + username + " successfully.");
        }
    }
}
