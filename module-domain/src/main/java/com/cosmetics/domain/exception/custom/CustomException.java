package com.cosmetics.domain.exception.custom;

import lombok.Getter;

@Getter //errorManagement 때문에
public class CustomException extends RuntimeException{
    private CustomErrorManagement errorManagement;

    public CustomException(CustomErrorManagement errorManagement) {
        super(errorManagement.getMessage());
        this.errorManagement = errorManagement;
    }

}
