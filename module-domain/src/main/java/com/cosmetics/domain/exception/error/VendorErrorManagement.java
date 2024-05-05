package com.cosmetics.domain.exception.error;

import com.cosmetics.domain.exception.custom.CustomErrorManagement;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum VendorErrorManagement implements CustomErrorManagement {

    VENDOR_ERROR_MANAGEMENT("업체 등록에 실패하였습니다 잠시 후에 시도해 주세요", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;

    VendorErrorManagement(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getName() {
        return name();
    }
}
