package com.andersenlab.adamovichjr.securityjwt.service;

import com.andersenlab.adamovichjr.securityjwt.model.MyUserDetails;
import com.andersenlab.adamovichjr.securityjwt.model.Role;
import com.andersenlab.adamovichjr.securityjwt.model.UserFromDataBase;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public MyUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserFromDataBase userFromDataBase = findUserFromDataBaseByUserName(userName);
        return new MyUserDetails(userFromDataBase);
    }

    private UserFromDataBase findUserFromDataBaseByUserName(String userName){
        UserFromDataBase userFromDataBase = new UserFromDataBase();
        userFromDataBase.setId(1L);
        userFromDataBase.setUserName("user");
        userFromDataBase.setPassword("123");
        userFromDataBase.setActive(true);
        userFromDataBase.setRole(Role.ADMIN);
        userFromDataBase.setAddress("Minsk");
        userFromDataBase.setMail("user@mail.ru");
        return userFromDataBase;
    }
}
