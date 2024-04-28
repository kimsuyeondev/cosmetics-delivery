package com.cosmetics.goods.controller;

import com.cosmetics.goods.GoodsItem;
import com.cosmetics.goods.GoodsMgmt;
import com.cosmetics.goods.service.GoodsService;
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

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 컨트롤러 단위 테스트
 * WebMvcTest 둘의 차이를 즈은혀 모르겠다..
 * */
@WebMvcTest(GoodsController.class)
@Slf4j
public class GoodsControllerTest2 {

    @MockBean
    private GoodsService goodsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("상품등록")
    public void goodsSave() throws Exception {
        //given
        GoodsMgmt goodsMgmt = requestGoods();

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8080/v1/goods")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(goodsMgmt)));

        //then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("resultCode").value("0000"));

        log.info(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    @DisplayName("상품조회")
    public void goodsSearch() throws Exception {
        //given
        String goodsNo = "2024050100001";
        GoodsMgmt responseMgmt = responseGoods();
        given(goodsService.findGoods(goodsNo)).willReturn(responseMgmt); //상품조회 시 서비스는 해당 상품을 응답할 것이다.

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/v1/goods/"+goodsNo));
        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("goodsNo").exists())
                .andExpect(jsonPath("goodsNm").exists());

        log.info(resultActions.andReturn().getResponse().getContentAsString(Charset.forName("UTF-8")));
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
