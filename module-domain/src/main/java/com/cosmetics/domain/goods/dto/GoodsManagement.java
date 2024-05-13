package com.cosmetics.domain.goods.dto;

import com.cosmetics.domain.response.ResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GoodsManagement extends ResponseDto {
    private String goodsNo;//seq

    @NotBlank
    private String category;

    @NotBlank
    private String goodsNm;

    @Min(0)
    private long salePrice;

    @Min(0)
    private long marketPrice;

    @Min(0)
    private long supplyPrice;

    @NotBlank
    private String vendorId;

    @Min(value = 1, message = "재고는 1개 이상 이어야 합니다.")
    private int stockQty;

    @NotBlank
    private String brandNm;

    private String saleStartDtime;
    private String saleEndDtime;

    @Valid
    private List<GoodsItemManagement> item;
    private String image;
    private String addImage;

    private String resultCode;
    private String resultMsg;

    @Builder
    public GoodsManagement(String goodsNo,
                           String category,
                           String goodsNm,
                           long salePrice,
                           long marketPrice,
                           long supplyPrice,
                           String vendorId,
                           int stockQty,
                           String brandNm,
                           String saleStartDtime,
                           String saleEndDtime,
                           List<GoodsItemManagement> item,
                           String image,
                           String addImage,
                           String resultCode,
                           String resultMsg) {
        super(resultCode, resultMsg);
        this.goodsNo = goodsNo;
        this.category = category;
        this.goodsNm = goodsNm;
        this.salePrice = salePrice;
        this.marketPrice = marketPrice;
        this.supplyPrice = supplyPrice;
        this.vendorId = vendorId;
        this.stockQty = stockQty;
        this.brandNm = brandNm;
        this.saleStartDtime = saleStartDtime;
        this.saleEndDtime = saleEndDtime;
        this.item = item;
        this.image = image;
        this.addImage = addImage;
    }

    public void createGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

}
