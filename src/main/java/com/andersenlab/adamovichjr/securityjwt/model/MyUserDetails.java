package com.andersenlab.adamovichjr.securityjwt.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

public class MyUserDetails extends User {

    private Long id;
    private String mail;

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
        this.mail = userFromDataBase.getMail();
    }
//    public MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//    }
//
//    public MyUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
