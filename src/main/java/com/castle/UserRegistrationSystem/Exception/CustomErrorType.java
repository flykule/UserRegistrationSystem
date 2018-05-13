package com.castle.UserRegistrationSystem.Exception;

import com.castle.UserRegistrationSystem.dto.UserDTO;

public class CustomErrorType extends UserDTO {
    private String errorMessgae;

    public CustomErrorType(String errorMessgae) {
        this.errorMessgae = errorMessgae;
    }

    public String getErrorMessgae() {
        return errorMessgae;
    }
}
