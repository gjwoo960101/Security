package com.spring.security.service;

import com.spring.security.dto.JoinDTO;
import com.spring.security.entity.UserEntity;
import com.spring.security.repositroy.UserRepositroy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepositroy userRepositroy;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepositroy userRepositroy,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepositroy = userRepositroy;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO){

        if(!userRepositroy.existsByUsername(joinDTO.getUsername())){
            UserEntity data = new UserEntity();
            data.setUsername(joinDTO.getUsername());
            data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
            data.setRole("ROLE_ADMIN");


            userRepositroy.save(data);
        }else{
            System.out.println("중복된 데이터 발생");
        }

    }


}
