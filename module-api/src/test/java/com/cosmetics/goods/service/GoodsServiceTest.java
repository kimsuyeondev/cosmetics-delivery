package com.cosmetics.goods.service;

import com.cosmetics.goods.GoodsItem;
import com.cosmetics.goods.GoodsMgmt;
import com.cosmetics.goods.repository.GoodsRepository;
import com.cosmetics.goods.service.impl.GoodsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class GoodsServiceTest {
    @InjectMocks
    private GoodsServiceImpl goodsService;

    @Mock
    private GoodsRepository goodsRepository;

    @Test
    @DisplayName("상품등록")
    public void 상품등록() throws Exception {

        //given
        GoodsMgmt requestGoods = requestGoods();
        GoodsMgmt responseGoods = responseGoods();

        //어떤 상품이어도 responseGoods가 반환되어야한다. 근데 any에 왜 requestGoods를 넣으면 오류가 나는지 이해가 안간다.ㅠ
        doReturn(responseGoods).when(goodsRepository).save(any(GoodsMgmt.class));

        //when
        GoodsMgmt resultGoods = goodsRepository.save(requestGoods);

        assertThat(resultGoods.getGoodsNo()).isEqualTo("2024050100001");
    }

    @Test
    @DisplayName("상품조회")
    public void 상품조회() throws Exception {

        //given
        GoodsMgmt responseGoods = responseGoods();

        doReturn(responseGoods).when(goodsRepository).findGoods(any(String.class));

        //when
        GoodsMgmt resultGoods = goodsRepository.findGoods("2024050100001");

        assertThat(resultGoods.getGoodsNo()).isEqualTo("2024050100001");
        assertThat(resultGoods.getGoodsNm()).isEqualTo("닥터스킨");
        assertThat(resultGoods.getItem().get(1).getItemNm()).isEqualTo("지성용");
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

    private static GoodsMgmt responseGoods() {
        //item
        List<GoodsItem> items = new ArrayList<>();

        items.add(GoodsItem.builder()
                .itemNm("건성용")
                .itemQty(50).build());
        items.add(GoodsItem.builder()
                .itemNm("지성용")
                .itemQty(30).build());

        return GoodsMgmt.builder()
                .goodsNo("2024050100001")
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
