package com.cosmetics.domain.exception.error;

import com.cosmetics.domain.exception.custom.CustomErrorManagement;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum GoodsErrorManagement implements CustomErrorManagement {

    GOODS_SAVE_ERROR("상품 등록에 실패하였습니다  잠시 후에 시도해 주세요", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    GoodsErrorManagement(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getName() {
        return name();
    }
}
