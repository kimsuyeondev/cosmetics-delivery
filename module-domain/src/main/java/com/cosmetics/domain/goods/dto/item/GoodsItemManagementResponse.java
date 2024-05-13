package com.cosmetics.domain.goods.dto.item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoodsItemManagementResponse {
    private Long itemNo;

    private String itemNm;

    private int itemQty;

    @Builder
    public GoodsItemManagementResponse(Long itemNo, String itemNm, int itemQty) {
        this.itemNo = itemNo;
        this.itemNm = itemNm;
        this.itemQty = itemQty;
    }

    public GoodsItemManagementResponse(GoodsItemManagement goodsItemManagement) {
        this.itemNo = goodsItemManagement.getItemNo();
        this.itemNm = goodsItemManagement.getItemNm();
        this.itemQty = goodsItemManagement.getItemQty();
    }
}
