package com.cosmetics.goods;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GoodsMgmt {
    private String category;
    private String goodsNm;
    private String goodsNo;
    private long salePrice;
    private long marketPrice;
    private long supplyPrice;
    private String vendorId;
    private int stockQty;
    private String brandNm;
    private String saleStartDtime;
    private String saleEndDtime;
    private List<GoodsItem> item;
    private String image;
    private String addImage;
}
