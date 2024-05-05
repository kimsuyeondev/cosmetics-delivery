package com.cosmetics.api.exception.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FieldErrorResponse{
    private String field;
    private String message;

    @Builder(builderMethodName = "fieldErrorResponseBuilder")
    public FieldErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
