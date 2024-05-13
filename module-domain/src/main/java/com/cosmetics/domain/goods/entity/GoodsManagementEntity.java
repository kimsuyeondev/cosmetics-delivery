package com.cosmetics.domain.goods.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@Table(name = "PV_GOODS")
public class GoodsManagementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GOODS_NO")
    private Long goodsNo;

    private String category;

    private String goodsNm;

    private long salePrice;

    private long marketPrice;

    private long supplyPrice;

    private Long vendorId;

    private int stockQty;

    private String brandNm;

    private String saleStartDtime;

    private String saleEndDtime;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime insertDtime;

    @LastModifiedDate
    private LocalDateTime updateDtime;

    @OneToMany(mappedBy = "goodsManagementEntity", cascade = CascadeType.PERSIST) // 보통 외래키가 있는 쪽이 주인, 주인은 mappyedBy를 설정하지 않음
    private List<GoodsItemManagementEntity> items = new ArrayList<>();

    private String image;

    private String addImage;

    @Builder
    public GoodsManagementEntity(Long goodsNo,
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
                                 //List<GoodsItemManagementEntity> items,
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
        this.image = image;
        this.addImage = addImage;
        //this.items = items;
        this.insertDtime = LocalDateTime.now();
        this.updateDtime = LocalDateTime.now();
    }

    public void addItems(List<GoodsItemManagementEntity> items) {
        this.items.addAll(items);
        items.forEach(it -> it.addGoods(this));
    }



}
