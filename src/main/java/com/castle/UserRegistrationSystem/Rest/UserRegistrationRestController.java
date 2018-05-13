package com.castle.UserRegistrationSystem.Rest;

import com.castle.UserRegistrationSystem.dto.UserDTO;
import com.castle.UserRegistrationSystem.repository.UserJpaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

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
    public ResponseEntity<List<UserDTO>> listAllUsers() {
        List<UserDTO> users = mUserJpaRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody final UserDTO userDTO) {
        mUserJpaRepository.save(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") final Long id) {
        Optional<UserDTO> optionalUserDTO = mUserJpaRepository.findById(id);
        return optionalUserDTO.map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK)).orElse(null);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable("id") final Long id,
            @RequestBody UserDTO userDTO
    ) {
        UserDTO currentUser = userDTO;
        Optional<UserDTO> optionalUserDTO = mUserJpaRepository.findById(id);
        if (optionalUserDTO.isPresent()) {
            currentUser = optionalUserDTO.get();
        }

        currentUser.setName(userDTO.getName());
        currentUser.setAddress(userDTO.getAddress());
        currentUser.setEmail(userDTO.getEmail());

        mUserJpaRepository.saveAndFlush(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") final Long id){
        mUserJpaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
