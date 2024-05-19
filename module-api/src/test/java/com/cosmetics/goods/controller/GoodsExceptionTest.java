package com.cosmetics.goods.controller;

import com.cosmetics.domain.exception.custom.CustomException;
import com.cosmetics.domain.goods.dto.GoodsManagement;
import com.cosmetics.domain.goods.dto.GoodsManagementRequest;
import com.cosmetics.domain.goods.dto.item.GoodsItemManagementRequest;
import com.cosmetics.domain.goods.service.GoodsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
* 확인필요 이렇게도 테스트를 할까요?? 커스텀예외를 테스트하기 위해 상품등록시 서비스비즈니스와 컨트롤러 익셉션핸들러 테스트를 해보고 싶었습니다.
 * 테스트 실패
 * goodsService 안의 goodsRepository를 건들수가 없어서 성공으로 나오며, 테스트 실패하였습니다
 * 커스텀예외의 핸들러를 테스트하고 싶은데 어떻게 해야할까요?
* */

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class GoodsExceptionTest {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("상품등록 시 내부오류로 상품등록이 실패했을 경우_커스텀 예외_CustomExceptionHandler 테스트")
    public void saveGoodsFailErrorTest() throws Exception {
        GoodsManagementRequest goodsManagement = requestGoods();
        //테스트실패
        //java.lang.NullPointerException: Cannot invoke "com.cosmetics.domain.goods.dto.GoodsManagement.toEntity()" because "goodsManagement" is null
        when(goodsService.save(any(GoodsManagement.class))).thenThrow(CustomException.class);

        mockMvc.perform(
                post("http://localhost:8080/v1/goods")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(goodsManagement)))
                .andDo(print())
                .andExpect(jsonPath("errorCode").value("GOODS_SAVE_ERROR"))
                .andExpect(jsonPath("errorMessage").value("상품 등록에 실패하였습니다  잠시 후에 시도해 주세요"));
    }

    private static GoodsManagementRequest requestGoods() {
        //item
        List<GoodsItemManagementRequest> items = new ArrayList<>();

        items.add(GoodsItemManagementRequest.builder()
                .itemNm("건성용")
                .itemQty(50).build());
        items.add(GoodsItemManagementRequest.builder()
                .itemNm("지성용")
                .itemQty(30).build());

        return GoodsManagementRequest.builder()
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
}
