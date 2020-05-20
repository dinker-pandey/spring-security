package com.dp.spring.learning.springsecurity.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PreAuthenticatedAuthenticationToken token = (PreAuthenticatedAuthenticationToken) authentication;
        User currentUser = (User) token.getPrincipal();
        return new PreAuthenticatedAuthenticationToken(currentUser, "");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PreAuthenticatedAuthenticationToken.class.equals(aClass);
    }
}
