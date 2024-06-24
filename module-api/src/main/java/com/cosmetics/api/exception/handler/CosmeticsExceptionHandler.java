package com.cosmetics.api.exception.handler;

import com.cosmetics.api.exception.response.ErrorResponse;
import com.cosmetics.api.exception.response.FieldErrorResponse;
import com.cosmetics.core.exception.ErrorManagement;
import com.cosmetics.core.exception.common.CommonErrorManagement;
import com.cosmetics.domain.exception.custom.CustomErrorManagement;
import com.cosmetics.domain.exception.custom.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestControllerAdvice
@Slf4j
public class CosmeticsExceptionHandler {

    /**
     * validation exception handler
     * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorManagement errorManagement = CommonErrorManagement.INVALID_PARAMETER;
        return getFildErrorResponse(e, errorManagement);
    }

    /**
     *  validation field 오류 상세 응답 메소드
     * */
    private static ErrorResponse getFildErrorResponse(MethodArgumentNotValidException e, ErrorManagement errorManagement) {
        List<FieldErrorResponse> fieldErrorResponseList = new ArrayList<>();

        for (FieldError fieldError : e.getFieldErrors()) {
            fieldErrorResponseList.add(new FieldErrorResponse(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        return ErrorResponse.builder().errorCode(errorManagement.getName())
                .errorMessage(errorManagement.getMessage())
                .fieldErrorList(fieldErrorResponseList).build();
    }

    /**
     * IllegalArgumentException exception handler
     * */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorManagement errorManagement = CommonErrorManagement.INVALID_PARAMETER;
        log.error("Handling IllegalArgumentException: {}", e.getMessage());
        return ErrorResponse.builder()
                .errorCode(errorManagement.getName())
                .errorMessage(Optional.ofNullable(e.getMessage()).orElse(errorManagement.getMessage())).build();
    }

    /**
     * 파라미터 타입 불일치 handler
     * */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatchException(HttpMessageNotReadableException e) {
        ErrorManagement errorManagement = CommonErrorManagement.INVALID_PARAMETER;

        return getErrorResponse(e, errorManagement);
    }

    /**
     * 커스텀 사용자 정의 exception handler
     * */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        CustomErrorManagement customErrorManagement = e.getErrorManagement();
        return new ResponseEntity<>(getErrorResponse(e, customErrorManagement), customErrorManagement.getHttpStatus());
    }

    /**
     * 응답 메소드
     * */
    private ErrorResponse getErrorResponse(Exception e, ErrorManagement errorManagement) {
        return ErrorResponse.builder()
                .errorCode(errorManagement.getName()).errorMessage(errorManagement.getMessage()).build();
    }

    /**
     * RuntimeException exception handler
     * */
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handleRuntimeException(RuntimeException e) {
        ErrorManagement errorManagement = CommonErrorManagement.INVALID_PARAMETER;

        log.error("Handling RuntimeException: {}", e.getMessage());
        return ErrorResponse.builder()
                .errorCode(errorManagement.getName())
                .errorMessage(Optional.ofNullable(e.getMessage()).orElse(errorManagement.getMessage())).build();
    }

}
