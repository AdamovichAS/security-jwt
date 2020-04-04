package com.andersenlab.adamovichjr.securityjwt.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyUserDetails extends User {

    private Map fieldsFromUserToJWT = new HashMap<String,Object>(); //сюда передаем значения для JWT

    public MyUserDetails(UserFromDataBase userFromDataBase){
        super(userFromDataBase.getUserName(),
                userFromDataBase.getPassword(),
                true,
                true,
                true,
                userFromDataBase.isActive(),
                Collections.singletonList((GrantedAuthority) () -> "ROLE_" + userFromDataBase.getRole().toString())
        );
        fieldsFromUserToJWT.put("id",userFromDataBase.getId());
        fieldsFromUserToJWT.put("role",userFromDataBase.getRole());
    }

    public Map getFieldsFromUserToJWT() {
        return fieldsFromUserToJWT;
    }
}
