package com.cosmetics.goods.service;

import com.cosmetics.domain.goods.dto.GoodsManagement;
import com.cosmetics.domain.goods.dto.GoodsManagementResponse;
import com.cosmetics.domain.goods.dto.item.GoodsItemManagement;
import com.cosmetics.domain.goods.entity.GoodsManagementEntity;
import com.cosmetics.domain.goods.repository.GoodsRepository;
import com.cosmetics.domain.goods.service.GoodsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

/**
 * 상품 서비스 테스트 시나리오
 * [상품등록]
 * 등록된 상품엔티티가 서비스 객체로 정상 변환된다.
 * 서비스 객체가 컨트롤러에 반환될 응답객체로 정상 변환된다.
 * 상품등록이 정상적으로 응답값으로 0000, "등록성공" 이 업데이트 된다.

 * [상품조회]
 * 조회한 상품이 존재하지 않는경우 익셉션을 발생시킨다.
 * 상품조회가 정상적으로 조회된다.
 */
@ExtendWith(MockitoExtension.class)
public class GoodsServiceTest {
    @InjectMocks
    private GoodsService goodsService;

    @Mock
    private GoodsRepository goodsRepository;

    @Test
    @DisplayName("상품 엔티티가 서비스 객체로 변환된다. ")
    public void 엔티티_서비스객체_변환() throws Exception {
        //given
        GoodsManagementEntity goodsEntity = responseGoods().toEntity();

        //when entity -> dto
        GoodsManagement goodsManagement = GoodsManagement.toDto(goodsEntity);

        //then
        assertThat(goodsManagement).usingRecursiveComparison().ignoringFields("items", "insertDtime", "updateDtime").isEqualTo(goodsEntity);
    }

    @Test
    @DisplayName("서비스 객체가 컨트롤러에 반환될 응답객체로 정상 변환된다.")
    public void 서비스객체_응답객체_변환() throws Exception {
        //given
        GoodsManagementEntity goodsEntity = responseGoods().toEntity();
        GoodsManagement goodsManagement = GoodsManagement.toDto(goodsEntity);

        //when dto -> response
        GoodsManagementResponse goodsManagementResponse = GoodsManagementResponse.toResponseDto(goodsManagement);

        //then
        assertThat(goodsManagementResponse).usingRecursiveComparison().ignoringFields("resultCode", "resultMsg").isEqualTo(goodsManagement);
    }

    @Test
    @DisplayName("상품등록에 성공한다.")
    public void 상품등록() throws Exception {
        //given
        GoodsManagement requestGoods = requestGoods();
        GoodsManagement responseGoods = responseGoods();
        GoodsManagementEntity responseEntity = responseGoods.toEntity();

        //when
        when(goodsRepository.save(any(GoodsManagementEntity.class))).thenReturn(responseEntity);

        //then
        GoodsManagementResponse resultGoods = goodsService.save(requestGoods);

        assertThat(resultGoods.getGoodsNo()).isEqualTo(1L);
        assertThat(resultGoods.getResultCode()).isEqualTo("0000");
        assertThat(resultGoods.getResultMsg()).isEqualTo("등록성공");
    }

    @Test
    @DisplayName("조회한 상품이 존재하지 않는경우 익셉션을 발생시킨다.")
    public void 상품_미존재() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            goodsService.findByGoodsNo(222L);
        });
    }

    @Test
    @DisplayName("상품조회에 성공한다.")
    public void 상품조회() throws Exception {
        //given
        GoodsManagement responseGoods = responseGoods();
        GoodsManagementEntity responseEntity = responseGoods.toEntity();

        when(goodsRepository.findByGoodsNo(any(Long.class))).thenReturn(Optional.of(responseEntity));

        //when
        GoodsManagementResponse resultGoods = goodsService.findByGoodsNo(1L);

        assertThat(resultGoods.getGoodsNo()).isEqualTo(1L);
        assertThat(resultGoods.getItems().get(0).getItemNo()).isEqualTo(1L);
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

    private static GoodsManagement responseGoods() {
        //item
        List<GoodsItemManagement> items = new ArrayList<>();

        items.add(GoodsItemManagement.builder()
                .itemNo(1L)
                .itemNm("건성용")
                .itemQty(50).build());
        items.add(GoodsItemManagement.builder()
                .itemNo(2L)
                .itemNm("지성용")
                .itemQty(30).build());

        return GoodsManagement.builder()
                .goodsNo(1L)
                .category("스킨케어")
                .goodsNm("닥터스킨")
                .marketPrice(15000)
                .salePrice(12000)
                .supplyPrice(10000)
                .vendorId(1l)
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
