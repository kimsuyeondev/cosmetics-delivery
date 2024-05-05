package com.cosmetics.goods.controller;

import com.cosmetics.goods.GoodsItem;
import com.cosmetics.goods.GoodsMgmt;
import com.cosmetics.goods.service.GoodsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 컨트롤러 단위 테스트
 * mockito 사용
 * mockito 는 의존성이 있는 빈들이 하드코딩 마냥 어떤 값을 반환해야할 경우를 설정 stub 메소드를 제공한다.
 * when을 사용할때 아래 3가지 메소드가 자주 사용, when은 앞으로 뒤로 위치 맘대로 가능
 * doReturn() //가짜객체가 어떤 리턴값을 반환
 * doNoting() //가짜객체가 아무 값도 반환x
 * doThrow()  //가짜객체가 예외를 반환 * */
@ExtendWith(MockitoExtension.class)
@Slf4j
public class GoodsControllerTest {

    @InjectMocks
    private GoodsController goodsController;

    @Mock
    private GoodsService goodsService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(goodsController).build();
    }

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
        doReturn(responseMgmt).when(goodsService).findGoods(goodsNo); //상품조회 시 서비스는 해당 상품을 응답할 것이다.
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
