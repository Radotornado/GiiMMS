package de.uni_passau.fim.giimms.util;

import de.uni_passau.fim.giimms.GiiMmsApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Logout extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {

        if (authentication != null) {
            Logger log = LoggerFactory.getLogger(GiiMmsApplication.class);
            log.error(authentication.toString());
            // do something
        }

        setDefaultTargetUrl("/login");
        super.onLogoutSuccess(request, response, authentication);
    }
}
