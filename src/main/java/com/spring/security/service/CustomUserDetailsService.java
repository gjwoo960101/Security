package com.spring.security.service;

import com.spring.security.dto.CustomUserDetails;
import com.spring.security.entity.UserEntity;
import com.spring.security.repositroy.UserRepositroy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepositroy userRepositroy;

    public CustomUserDetailsService(UserRepositroy userRepositroy) {
        this.userRepositroy = userRepositroy;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepositroy.findByUsername(username);

        if(user != null){
            return new CustomUserDetails(user);
        }


        return null;
    }
}
