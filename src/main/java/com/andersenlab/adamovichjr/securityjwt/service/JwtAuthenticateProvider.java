package com.andersenlab.adamovichjr.securityjwt.service;

import com.andersenlab.adamovichjr.securityjwt.model.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticateProvider implements AuthenticationProvider {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        MyUserDetails userDetails = myUserDetailsService.loadUserByUsername(name);
        if (userDetails.getPassword().equals(password)) {
            //Через этот конструктор создается аутенцифицированный токен
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(name, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(userDetails.getFieldsFromUserToJWT());
            return usernamePasswordAuthenticationToken;
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
