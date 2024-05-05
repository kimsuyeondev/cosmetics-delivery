package com.cosmetics.core.exception.common;

import com.cosmetics.core.exception.ErrorManagement;
import lombok.Getter;
/**
 * 공통 오류 관리
* */
@Getter
public enum CommonErrorManagement implements ErrorManagement {

    INVALID_PARAMETER("유효하지 않는 값입니다"),
    RESOURCE_NOT_FOUND("존재하지 않습니다"),
    INTERNAL_SERVER_ERROR("죄송합니다. 잠시 후에 시도해 주세요");

    private final String message;

    CommonErrorManagement(String message) {
        this.message = message;
    }

    @Override
    public String getName() {
        return name();
    }
}
