package com.andersenlab.adamovichjr.securityjwt.controller;

import com.andersenlab.adamovichjr.securityjwt.model.AuthenticationRequest;
import com.andersenlab.adamovichjr.securityjwt.model.AuthenticationResponse;
import com.andersenlab.adamovichjr.securityjwt.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;


    @GetMapping("/hello" )
    public String firstPage() {
        return "Secret page";
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            authentication = authenticationManager.authenticate(authenticationToken);
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final String jwt = jwtTokenUtil.generateToken(authentication);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}

