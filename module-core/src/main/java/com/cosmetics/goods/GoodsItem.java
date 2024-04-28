package com.cosmetics.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GoodsItem {
    String itemNm;
    int itemQty;

    public GoodsItem() {

    }
}
