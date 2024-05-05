package com.cosmetics.goods.repository;

import com.cosmetics.goods.GoodsItem;
import com.cosmetics.goods.GoodsMgmt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class GoodsRepositoryTest {

    @InjectMocks
    private GoodsRepository goodsRepository;

    @Test
    public void 상품등록() throws Exception {
        //given
        GoodsMgmt goodsMgmt = requestGoods();

        //when
        goodsRepository.save(goodsMgmt);

        System.out.println(new ObjectMapper().writeValueAsString(goodsMgmt));
        //then
        assertNotNull(goodsMgmt.getGoodsNo());

    }

    @Test
    public void 상품조회() throws Exception {
       상품등록();
       GoodsMgmt goodsMgmt = goodsRepository.findGoods("240501100001");
       assertNotNull(goodsMgmt.getGoodsNm());
    }

    private static GoodsMgmt requestGoods() {
        //item
        List<GoodsItem> items = new ArrayList<>();

        items.add(GoodsItem.builder()
                .itemNm("건성용")
                .itemQty(50).build());
        items.add(GoodsItem.builder()
                .itemNm("지성용")
                .itemQty(30).build());

        return GoodsMgmt.builder()
                .category("스킨케어")
                .goodsNm("닥터스킨")
                .marketPrice(15000)
                .salePrice(12000)
                .supplyPrice(10000)
                .vendorId("lv202400002")
                .stockQty(80)
                .brandNm("닥터펫")
                .saleStartDtime("2024-05-01 00:00:00")
                .saleEndDtime("2024-08-01 00:00:00")
                .image("https://cdn.localhost:8081/images/lv202400002/goods/image_1.png")
                .addImage("https://cdn.localhost:8081/images/lv202400002/goods/image_2.png")
                .item(items)
                .build();
    }
}
