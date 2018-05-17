package com.castle.UserRegistrationSystem.repository;


import com.castle.UserRegistrationSystem.dto.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoJpaRepository extends JpaRepository<UserInfo,Long> {
    UserInfo findByUserName(String name);
}
