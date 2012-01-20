package com.tc.webatm.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.constraints.NotNull;

import com.tc.webatm.model.User;
import com.tc.webatm.service.UserService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import com.google.common.base.Charsets;

public class BasicAuthFilter implements Filter  {
    private String realm;
    
    public void destroy() { }

    @Override
    public void doFilter(@NotNull final ServletRequest request, @NotNull final ServletResponse response, @NotNull final FilterChain chain ) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        final String auth = httpRequest.getHeader( "Authorization" );
        if ( auth != null ) {
            final int index = auth.indexOf(' ');
            if ( index > 0 ) {
                String decoded = new String(Base64.decodeBase64(auth.substring(index ).getBytes(Charsets.UTF_8)));
                final String[] credentials = StringUtils.split(decoded, ':');

                if (credentials.length == 2) {
                    User u = new User();
                    u.setEmail(credentials[0]);
                    u.setPassword(credentials[1]);

                    if (UserService.ADMIN_EMAIL.equals(credentials[0]) && UserService.ADMIN_PASSWORD.equals(credentials[1]) ) {
                        UserService.setLoggedUser(u);
                    } else {//todo: fetch users from db
                        if (credentials[0].equals("user@test.com") && credentials[1].equals("test")) {
                            UserService.setLoggedUser(u);
                        }
                    }
                }
            }
        }

        if (UserService.isUserLogged()) {
            chain.doFilter( httpRequest, httpResponse );
            return;
        }

        httpResponse.setHeader( "WWW-Authenticate", "Basic realm=\"" + realm + "\"" );
        httpResponse.sendError( HttpServletResponse.SC_UNAUTHORIZED );
    }

    @Override
    public void init(@NotNull final FilterConfig config ) throws ServletException {
        realm = config.getInitParameter("realm");

        if ( StringUtils.isBlank(realm) ) {
            throw new ServletException( "No realm provided in filter configuration" );
        }
    }
}