package com.castle.UserRegistrationSystem.Exception;

import com.castle.UserRegistrationSystem.dto.UserDTO;

public class CustomErrorType extends UserDTO {
    private String errorMessage;

    public CustomErrorType(String errorMessgae) {
        this.errorMessage = errorMessgae;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
