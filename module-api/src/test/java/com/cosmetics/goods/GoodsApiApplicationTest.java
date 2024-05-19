package com.cosmetics.goods;

import com.cosmetics.domain.goods.dto.GoodsManagement;
import com.cosmetics.domain.goods.dto.GoodsManagementRequest;
import com.cosmetics.domain.goods.dto.GoodsManagementResponse;
import com.cosmetics.domain.goods.dto.item.GoodsItemManagementRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class GoodsApiApplicationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

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

    @DisplayName("상품등록")
    @Test
    @Order(1)
    public void 상품등록() throws Exception{
        String url = "http://localhost:" + port + "/v1/goods";
        GoodsManagementRequest goodsManagementRequest = requestGoods();

        ResponseEntity<GoodsManagementResponse> responseEntity = testRestTemplate.postForEntity(url, goodsManagementRequest, GoodsManagementResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertNotNull(responseEntity.getBody().getGoodsNo());
        assertThat(responseEntity.getBody().getResultCode()).isEqualTo("0000");
    }

    @DisplayName("상품조회")
    @Test
    @Order(2)
    public void 상품조회() throws Exception{
        String url = "http://localhost:" + port + "/v1/goods/{goodsNo}";
        ResponseEntity<GoodsManagement> responseEntity = testRestTemplate.getForEntity(url, GoodsManagement.class,1L);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getGoodsNm()).isEqualTo("닥터스킨");
    }

    @DisplayName("상품삭제")
    @Test
    @Order(3)
    public void 상품삭제() throws Exception{
        String url =  "http://localhost:" + port + "/v1/goods/{goodsNo}";
        ResponseEntity<Map> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, null, Map.class, 1L);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().get("resultCode")).isEqualTo("0000");
    }

    @Test
    @Order(4)
    @DisplayName("삭제할 상품번호가 존재하지 않습니다._ llegalArgumentExceptionHandler 테스트")
    public void illegalGoodsTest() throws Exception {
        //통합테스트에서도 이런 테스트를 해보는게 맞는걸까? 확인필요
        String goodsNo = "존재하지않는상품번호";
        String url =  "http://localhost:" + port + "/v1/goods/{goodsNo}";
        ResponseEntity<GoodsManagement> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, null, GoodsManagement.class, goodsNo);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(jsonPath("errorCode").value("INVALID_PARAMETER"));
        assertThat(jsonPath("errorMessage").value("존재하지 않는 상품입니다"));
    }
}
