package com.castle.UserRegistrationSystem.security.client;

import com.castle.UserRegistrationSystem.dto.UserDTO;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class UserRegistrationClient {
    private static RestTemplate sRestTemplate = new RestTemplate();
    private static final String USER_REGISTRATION_BASE_URL = "http://127.0.0.1:8090/api/user/";

    public UserDTO getUserById(final Long userId) {
        return sRestTemplate.getForObject(
                USER_REGISTRATION_BASE_URL + "/{id}",
                UserDTO.class, userId
        );
    }

    public UserDTO[] getAllUser() {
        return sRestTemplate.getForObject(
                USER_REGISTRATION_BASE_URL,
                UserDTO[].class
        );
    }

    public void updateUser(final Long userId, final UserDTO userDTO) {
        sRestTemplate.put(
                USER_REGISTRATION_BASE_URL + "/{id}",
                userDTO,
                userId
        );
    }

    public UserDTO createUser(final UserDTO user) {
        return sRestTemplate.postForObject(
                USER_REGISTRATION_BASE_URL,
                user,
                UserDTO.class
        );
    }

    public void deleteUser(final Long userId) {
        sRestTemplate.delete(
                USER_REGISTRATION_BASE_URL + "/{id}",
                userId
        );
    }

    public ResponseEntity<UserDTO> getUserByIdUsingExchangeAPI(final Long userId) {
        return sRestTemplate.exchange(USER_REGISTRATION_BASE_URL + "/{id}",
                HttpMethod.GET, new HttpEntity<>(new UserDTO()), UserDTO.class, userId);
    }

    public static void main(String[] args) {
        UserRegistrationClient client = new UserRegistrationClient();
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Ravi DaShaBi");
        userDTO.setAddress("aksljasfdkljfad");
        userDTO.setEmail("10086@qq.com");
        client.createUser(userDTO);
        UserDTO[] allUser = client.getAllUser();
        for (final UserDTO user : allUser) {
            System.out.println(user.getName() + ":" + user.getAddress());
        }

        UserDTO user = client.getUserById(1L);
        System.out.println(user.getName() + ":" + user.getAddress());

        user.setName("This is My new name.");
        client.updateUser(1L, user);

        ResponseEntity<UserDTO> exchangeAPI = client.getUserByIdUsingExchangeAPI(1L);
        System.out.println(exchangeAPI.getBody());

        client.deleteUser(1L);
    }
}
