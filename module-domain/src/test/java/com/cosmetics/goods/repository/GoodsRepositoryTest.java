package com.cosmetics.goods.repository;

import com.cosmetics.domain.goods.dto.GoodsManagement;
import com.cosmetics.domain.goods.dto.item.GoodsItemManagement;
import com.cosmetics.domain.goods.entity.GoodsManagementEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class GoodsRepositoryTest {

    @Test()
    @DisplayName("상품 서비스 객체가 상품 엔티티로 정상 변환된다.")
    public void 상품서비스객체_엔티티_변환() throws Exception {
        //given
        GoodsManagement requestGoods = requestGoods();

        //when dto->entity
        GoodsManagementEntity goodsManagementEntity = requestGoods.toEntity();

        //then
        assertThat(goodsManagementEntity).usingRecursiveComparison().ignoringFields("items", "insertDtime", "updateDtime").isEqualTo(requestGoods);
        //상품의 옵션
        assertThat(requestGoods.getItems()).hasSize(goodsManagementEntity.getItems().size());
        for (int i = 0; i < requestGoods.getItems().size(); i++) {
            assertThat(goodsManagementEntity.getItems().get(i)).usingRecursiveComparison().ignoringFields("insertDtime", "goodsManagementEntity", "updateDtime").isEqualTo(requestGoods.getItems().get(i));
        }
    }

    private static GoodsManagement requestGoods() {
        //item
        List<GoodsItemManagement> items = new ArrayList<>();

        items.add(GoodsItemManagement.builder()
                .itemNm("건성용")
                .itemQty(50).build());
        items.add(GoodsItemManagement.builder()
                .itemNm("지성용")
                .itemQty(30).build());

        return GoodsManagement.builder()
                .category("스킨케어_test")
                .goodsNm("닥터스킨")
                .marketPrice(15000)
                .salePrice(12000)
                .supplyPrice(10000)
                .vendorId(1L)
                .stockQty(80)
                .brandNm("닥터펫")
                .saleStartDtime("2024-05-01 00:00:00")
                .saleEndDtime("2024-08-01 00:00:00")
                .image("https://cdn.localhost:8081/images/lv202400002/goods/image_1.png")
                .addImage("https://cdn.localhost:8081/images/lv202400002/goods/image_2.png")
                .items(items)
                .build();
    }
}
