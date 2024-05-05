package com.cosmetics.goods.controller;

import com.cosmetics.domain.goods.dto.GoodsItemManagement;
import com.cosmetics.domain.goods.dto.GoodsManagement;
import com.cosmetics.domain.goods.repository.GoodsRepository;
import com.cosmetics.domain.goods.service.GoodsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
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
public class GoodsExceptionTest {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoodsRepository goodsRepository;

    @Test
    @DisplayName("상품등록 시 내부오류로 상품등록이 실패했을 경우_커스텀 예외_CustomExceptionHandler 테스트")
    public void saveGoodsFailErrorTest() throws Exception {
        GoodsManagement goodsManagement = requestGoods();

        doReturn(null).when(goodsRepository).save(any(GoodsManagement.class));
// Body = {"resultCode":"0000","resultMsg":"등록성공","goodsNo":"240501100001","category":"스킨케어","goodsNm":"닥터스킨","salePrice":12000,"marketPrice":15000,"supplyPrice":10000,"vendorId":"lv202400002","stockQty":80,"brandNm":"닥터펫","saleStartDtime":"2024-05-01 00:00:00","saleEndDtime":"2024-08-01 00:00:00","item":[{"itemNo":null,"itemNm":"건성용","itemQty":50},{"itemNo":null,"itemNm":"지성용","itemQty":30}],"image":"https://cdn.localhost:8081/images/lv202400002/goods/image_1.png","addImage":"https://cdn.localhost:8081/images/lv202400002/goods/image_2.png"}

        mockMvc.perform(
                post("http://localhost:8080/v1/goods")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(goodsManagement)))
                .andDo(print())
                .andExpect(jsonPath("errorCode").value("GOODS_SAVE_ERROR"))
                .andExpect(jsonPath("errorMessage").value("상품 등록에 실패하였습니다  잠시 후에 시도해 주세요"));
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
}
