package com.castle.UserRegistrationSystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.castle.UserRegistrationSystem.dto.UserDTO;

@Repository
public interface UserJpaRepository extends JpaRepository<UserDTO,Long> {
    UserDTO findByName(String name);
}
