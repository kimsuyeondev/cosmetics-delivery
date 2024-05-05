package com.cosmetics.goods.controller;

import com.cosmetics.api.goods.controller.GoodsController;
import com.cosmetics.domain.exception.custom.CustomException;
import com.cosmetics.domain.exception.error.GoodsErrorManagement;
import com.cosmetics.domain.goods.dto.GoodsItemManagement;
import com.cosmetics.domain.goods.dto.GoodsManagement;
import com.cosmetics.domain.goods.service.GoodsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 컨트롤러 단위 테스트
 */
@WebMvcTest(GoodsController.class)
@Slf4j
public class GoodsControllerTest2 {

    @MockBean
    private GoodsService goodsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("상품등록 파라미터가 누락되었을 경우_validation handler_MethodArgumentNotValidException 테스트")
    public void validGoodsTest() throws Exception {
        //given
        GoodsManagement goodsManagement = requestValidGoods();

        //when
        ResultActions resultActions = mockMvc.perform(
                post("http://localhost:8080/v1/goods")
                        .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(goodsManagement)));

        resultActions.andExpect(status().isBadRequest());
        resultActions.andDo(print());
        //then
        resultActions.andExpect(jsonPath("errorCode").value("INVALID_PARAMETER"));
        resultActions.andExpect(jsonPath("errorMessage").value("유효하지 않는 값입니다"));
        resultActions.andExpect(jsonPath("$.fieldErrorList[0].field").value("goodsNm"));
        resultActions.andExpect(jsonPath("$.fieldErrorList[0].message").value("must not be blank"));
        resultActions.andExpect(jsonPath("$.fieldErrorList[1].field").value("item[0].itemNm"));
        resultActions.andExpect(jsonPath("$.fieldErrorList[1].message").value("must not be blank"));
    }

    @Test
    @DisplayName("상품등록 시 내부오류로 상품등록이 실패했을 경우_커스텀 예외_CustomExceptionHandler 테스트")
    public void saveGoodsFailErrorTest() throws Exception {
        GoodsManagement goodsManagement = requestGoods();

        given(goodsService.save(goodsManagement)).willThrow(new CustomException(GoodsErrorManagement.GOODS_SAVE_ERROR));

        //when
        ResultActions resultActions = mockMvc.perform(
                post("http://localhost:8080/v1/goods")
                        .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(goodsManagement)));
        resultActions.andDo(print()).andExpect(jsonPath("errorCode").value("GOODS_SAVE_ERROR"));

        //확인필요 실패 illegalGoodsTest는 되는데 왜 이거는 안되는지 도저히 모르겠습니다
    }

    @Test
    @DisplayName("삭제할 상품번호가 존재하지 않습니다_IllegalArgumentExceptionHandler 테스트 ")
    public void illegalGoodsTest() throws Exception {
        String goodsNo = "2024050100001";
        given(goodsService.deleteGoods(goodsNo)).willThrow(new IllegalArgumentException("존재하지 않는 상품입니다"));

        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/v1/goods/{goodsNo}", goodsNo))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errorCode").value("INVALID_PARAMETER"))
                .andExpect(jsonPath("errorMessage").value("존재하지 않는 상품입니다"));
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

    private static GoodsManagement requestValidGoods() {
        //item
        List<GoodsItemManagement> items = new ArrayList<>();

        items.add(GoodsItemManagement.builder()
                //.itemNm("건성용")
                .itemQty(50).build());
        items.add(GoodsItemManagement.builder()
                .itemNm("지성용")
                .itemQty(30).build());

        return GoodsManagement.builder()
                .category("스킨케어")
                //.goodsNm("닥터스킨")
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
