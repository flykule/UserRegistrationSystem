package com.castle.UserRegistrationSystem.Rest;

import com.castle.UserRegistrationSystem.dto.UserDTO;
import com.castle.UserRegistrationSystem.repository.UserJpaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {
    public static final Logger logger =
            LoggerFactory.getLogger(UserRegistrationRestController.class);

    private UserJpaRepository mUserJpaRepository;

    @Autowired
    public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
        mUserJpaRepository = userJpaRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> listAllUsers(){
        List<UserDTO> users = mUserJpaRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


}
