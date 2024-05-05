package com.cosmetics.api.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldErrorResponse> fieldErrorList;

    @Builder
    public ErrorResponse(String errorCode,
                         String errorMessage,
                         List<FieldErrorResponse> fieldErrorList) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.fieldErrorList = fieldErrorList;
    }
}
