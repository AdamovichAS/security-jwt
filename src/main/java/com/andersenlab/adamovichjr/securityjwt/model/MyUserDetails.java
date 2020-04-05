package com.andersenlab.adamovichjr.securityjwt.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyUserDetails extends User {

    private Long id;

    public MyUserDetails(UserFromDataBase userFromDataBase){
        super(userFromDataBase.getUserName(),
                userFromDataBase.getPassword(),
                true,
                true,
                true,
                userFromDataBase.isActive(),
                Collections.singletonList((GrantedAuthority) () -> "ROLE_" + userFromDataBase.getRole().toString())
        );
        this.id = userFromDataBase.getId();

    }


    public Long getId() {
        return id;
    }
}
