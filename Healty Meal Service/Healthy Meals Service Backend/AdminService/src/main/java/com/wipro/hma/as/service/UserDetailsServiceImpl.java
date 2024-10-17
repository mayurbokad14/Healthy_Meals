package com.wipro.hma.as.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("admin")){
            return new User("admin", "$2a$12$Alr2R8rCEZHrzjB97Nw1RO0YbVEagVztjb6fGdQDWG8r2YOsY5j2O", new ArrayList<>());
        }
        else{
            throw new UsernameNotFoundException("Admin not found !!");
        }
    }
}
