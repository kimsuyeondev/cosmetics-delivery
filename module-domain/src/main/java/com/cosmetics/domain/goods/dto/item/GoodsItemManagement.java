package com.cosmetics.domain.goods.dto.item;

import com.cosmetics.domain.goods.entity.GoodsItemManagementEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoodsItemManagement {
    private Long itemNo;
    private String itemNm;
    private int itemQty;

    @Builder
    public GoodsItemManagement(String itemNm, int itemQty) {
        this.itemNm = itemNm;
        this.itemQty = itemQty;
    }

    public GoodsItemManagement(GoodsItemManagementRequest goodsItemManagementRequest) {
        this.itemNo = goodsItemManagementRequest.getItemNo();
        this.itemNm = goodsItemManagementRequest.getItemNm();
        this.itemQty = goodsItemManagementRequest.getItemQty();
    }

    public GoodsItemManagement(GoodsItemManagementEntity goodsItemManagementEntity) {
        this.itemNo = goodsItemManagementEntity.getItemNo();
        this.itemNm = goodsItemManagementEntity.getItemNm();
        this.itemQty = goodsItemManagementEntity.getItemQty();
    }

}
