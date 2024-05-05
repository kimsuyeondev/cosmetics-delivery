package com.cosmetics.goods.service;

import com.cosmetics.domain.goods.dto.GoodsItemManagement;
import com.cosmetics.domain.goods.dto.GoodsManagement;
import com.cosmetics.domain.goods.repository.GoodsRepository;
import com.cosmetics.domain.goods.service.impl.GoodsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

/**
 * 단위 테스트
 * mockito 사용
 * mockito 는 의존성이 있는 빈들이 하드코딩 마냥 어떤 값을 반환해야할 경우를 설정 stub 메소드를 제공한다.
 * when을 사용할때 아래 3가지 메소드가 자주 사용, when은 앞으로 뒤로 위치 맘대로 가능
 * doReturn() //가짜객체가 어떤 리턴값을 반환
 * doNoting() //가짜객체가 아무 값도 반환x
 * doThrow()  //가짜객체가 예외를 반환 *
 * 컨트롤러테스트는 보통 통합 테스트를 하는경우가 많다.
 * 서비스 비즈니스 테스트가 대부분
 */
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
        GoodsManagement requestGoods = requestGoods();
        GoodsManagement responseGoods = responseGoods();

        //어떤 상품이어도 responseGoods가 반환되어야한다. 근데 any에 왜 requestGoods를 넣으면 오류가 나는지 이해가 안간다.ㅠ
        doReturn(responseGoods).when(goodsRepository).save(any(GoodsManagement.class));

        //when
        GoodsManagement resultGoods = goodsService.save(requestGoods);

        assertThat(resultGoods.getGoodsNo()).isEqualTo("2024050100001");
    }

    @Test
    @DisplayName("상품조회")
    public void 상품조회() throws Exception {

        //given
        GoodsManagement responseGoods = responseGoods();

        doReturn(responseGoods).when(goodsRepository).findGoods(any(String.class));

        //when
        GoodsManagement resultGoods = goodsRepository.findGoods("2024050100001");

        assertThat(resultGoods.getGoodsNo()).isEqualTo("2024050100001");
        assertThat(resultGoods.getGoodsNm()).isEqualTo("닥터스킨");
        assertThat(resultGoods.getItem().get(1).getItemNm()).isEqualTo("지성용");
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

    private static GoodsManagement responseGoods() {
        //item
        List<GoodsItemManagement> items = new ArrayList<>();

        items.add(GoodsItemManagement.builder()
                .itemNm("건성용")
                .itemQty(50).build());
        items.add(GoodsItemManagement.builder()
                .itemNm("지성용")
                .itemQty(30).build());

        return GoodsManagement.builder()
//                .goodsNo("2024050100001")
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
