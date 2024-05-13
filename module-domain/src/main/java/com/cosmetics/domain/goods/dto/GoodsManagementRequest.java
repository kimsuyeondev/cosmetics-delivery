package com.cosmetics.domain.goods.dto;

import com.cosmetics.domain.goods.dto.item.GoodsItemManagement;
import com.cosmetics.domain.goods.dto.item.GoodsItemManagementRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class GoodsManagementRequest {
    private Long goodsNo;//seq

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

    @Min(value = 1, message = "업체 번호를 입력해주세요.")
    private Long vendorId;

    @Min(value = 1, message = "재고는 1개 이상 이어야 합니다.")
    private int stockQty;

    @NotBlank
    private String brandNm;

    private String saleStartDtime;
    private String saleEndDtime;

    @Valid
    private List<GoodsItemManagementRequest> item;
    private String image;
    private String addImage;

    @Builder
    public GoodsManagementRequest(Long goodsNo,
                                  String category,
                                  String goodsNm,
                                  long salePrice,
                                  long marketPrice,
                                  long supplyPrice,
                                  Long vendorId,
                                  int stockQty,
                                  String brandNm,
                                  String saleStartDtime,
                                  String saleEndDtime,
                                  List<GoodsItemManagementRequest> item,
                                  String image,
                                  String addImage) {
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

    public List<GoodsItemManagement> toGoodsItemManagementList(List<GoodsItemManagementRequest> item){
        return item.stream()
                .map(GoodsItemManagement::new).collect(Collectors.toList());
    }

    public GoodsManagement toServiceDto() {
        return GoodsManagement.builder()
                .goodsNo(goodsNo)
                .goodsNm(goodsNm)
                .category(category)
                .salePrice(salePrice)
                .marketPrice(marketPrice)
                .supplyPrice(supplyPrice)
                .vendorId(vendorId)
                .stockQty(stockQty)
                .brandNm(brandNm)
                .saleStartDtime(saleStartDtime)
                .saleEndDtime(saleEndDtime)
                .item(toGoodsItemManagementList(item))
                .image(image)
                .addImage(addImage)
                .build();
    }

}