package com.cosmetics.domain.goods.entity;

import com.cosmetics.domain.goods.dto.item.GoodsItemManagement;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@Table(name = "PV_ITEM")
public class GoodsItemManagementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_NO")
    private Long itemNo;

    private String itemNm;

    private int itemQty;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime insertDtime;

    @LastModifiedDate
    private LocalDateTime updateDtime;

    @ManyToOne
    @JoinColumn(name = "GOODS_NO")
    private GoodsManagementEntity goodsManagementEntity;

    @Builder
    public GoodsItemManagementEntity(Long itemNo, String itemNm, int itemQty, GoodsManagementEntity goodsManagementEntity) {
        this.itemNo = itemNo;
        this.itemNm = itemNm;
        this.itemQty = itemQty;
        this.goodsManagementEntity = goodsManagementEntity;
    }

    public GoodsItemManagementEntity(GoodsItemManagement goodsItemManagement) {
        this.itemNo = goodsItemManagement.getItemNo();
        this.itemNm = goodsItemManagement.getItemNm();
        this.itemQty = goodsItemManagement.getItemQty();
        this.insertDtime = LocalDateTime.now();
        this.updateDtime = LocalDateTime.now();
    }

    public void addGoods(GoodsManagementEntity goodsManagementEntity) {
        this.goodsManagementEntity = goodsManagementEntity;
    }

}
