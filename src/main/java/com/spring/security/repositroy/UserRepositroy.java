package com.spring.security.repositroy;

import com.spring.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositroy extends JpaRepository<UserEntity,Integer> {

    Boolean existsByUsername(String name);

    UserEntity findByUsername(String username);

}
