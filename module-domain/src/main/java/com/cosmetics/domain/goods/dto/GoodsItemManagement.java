package com.cosmetics.domain.goods.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GoodsItemManagement {
    private String itemNo;

    @NotBlank
    private String itemNm;

    @Min(value = 1, message = "재고는 최소 1개 이상이어야합니다")
    private int itemQty;

    public GoodsItemManagement() {
    }

    @Builder
    public GoodsItemManagement(String itemNm, int itemQty) {
        this.itemNm = itemNm;
        this.itemQty = itemQty;
    }

    public void changeItemQty(int itemQty) {
        this.itemQty = itemQty;
    }
}
