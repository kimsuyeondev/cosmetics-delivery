package com.cosmetics.domain.exception.custom;

import com.cosmetics.core.exception.ErrorManagement;
import org.springframework.http.HttpStatus;

public interface CustomErrorManagement extends ErrorManagement {
    HttpStatus getHttpStatus();
}
