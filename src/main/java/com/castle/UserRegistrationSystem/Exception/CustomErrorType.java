package com.castle.UserRegistrationSystem.Exception;

import com.castle.UserRegistrationSystem.dto.UserDTO;

public class CustomErrorType extends UserDTO {
    private String errorMessage;

    public CustomErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
