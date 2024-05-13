package com.cosmetics.domain.goods.dto.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoodsItemManagementRequest {
    private Long itemNo;

    @NotBlank
    private String itemNm;

    @Min(value = 1, message = "재고는 최소 1개 이상이어야합니다")
    private int itemQty;

    @Builder
    public GoodsItemManagementRequest(String itemNm, int itemQty) {
        this.itemNm = itemNm;
        this.itemQty = itemQty;
    }

    public void changeItemQty(int itemQty) {
        this.itemQty = itemQty;
    }
}
