package com.cosmetics.goods;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GoodsApiApplicationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    GoodsMgmt goodsMgmt = new GoodsMgmt();

    @BeforeEach
    public void setUp() throws Exception{
        goodsMgmt.setCategory("스킨케어");
        goodsMgmt.setGoodsNm("닥터스킨");
        goodsMgmt.setMarketPrice(15000);
        goodsMgmt.setSalePrice(12000);
        goodsMgmt.setSupplyPrice(10000);
        goodsMgmt.setVendorId("lv202400002");
        goodsMgmt.setStockQty(80);
        goodsMgmt.setBrandNm("닥터펫");
        goodsMgmt.setSaleStartDtime("2024-05-01 00:00:00");
        goodsMgmt.setSaleEndDtime("2024-08-01 00:00:00");
        goodsMgmt.setImage("https://cdn.localhost:8081/images/lv202400002/goods/image_1.png");
        goodsMgmt.setAddImage("https://cdn.localhost:8081/images/lv202400002/goods/image_2.png");

        //item
        List<GoodsItem> items = new ArrayList<>();
        GoodsItem item = new GoodsItem();
        item.setItemNm("건성용");
        item.setItemQty(50);
        items.add(item);
        item = new GoodsItem();
        item.setItemNm("지성용");
        item.setItemQty(30);
        items.add(item);
        goodsMgmt.setItem(items);
    }

    @DisplayName("상품등록")
    @Test
    @Order(1)
    public void 상품등록() throws Exception{
        String url = "http://localhost:8080/v1/goods";
        ResponseEntity<Map> responseEntity = testRestTemplate.postForEntity(url, goodsMgmt, Map.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertNotNull(responseEntity.getBody().get("goodsNo"));
        assertThat(responseEntity.getBody().get("resultCode")).isEqualTo("0000");
    }

    @DisplayName("상품조회")
    @Test
    @Order(2)
    public void 상품조회() throws Exception{
        String url = "http://localhost:8080/v1/goods/{goodsNo}";
        ResponseEntity<GoodsMgmt> responseEntity = testRestTemplate.getForEntity(url, GoodsMgmt.class,"240501100002");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getGoodsNm()).isEqualTo("닥터스킨");
    }

    @DisplayName("상품미조회_다른필드예외")
    @Test
    @Order(3)
    public void 상품_미조회_오류() {
        String url = "http://localhost:8080/v1/goods/{goodsNo}";
        assertThrows(BadRequestException.class, () -> {
            testRestTemplate.getForEntity(url, GoodsMgmt.class,"ㅋㅋㅋㅋ");
        });
/*
테스트 실패 왜 나는지 모르겠습니다.
org.opentest4j.AssertionFailedError: Expected org.apache.coyote.BadRequestException to be thrown, but nothing was thrown.

	at org.junit.jupiter.api.AssertionFailureBuilder.build(AssertionFailureBuilder.java:152)
	at org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:73)
	at org.junit.jupiter.api.AssertThrows.assertThrows(AssertThrows.java:35)
	at org.junit.jupiter.api.Assertions.assertThrows(Assertions.java:3115)
	at com.cosmetics.goods.GoodsApiApplicationTest.상품_미조회_오류(GoodsApiApplicationTest.java:84)
	at java.base/java.lang.reflect.Method.invoke(Method.java:580)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)

 */
    }

    @DisplayName("상품삭제")
    @Test
    @Order(4)
    public void 상품삭제() throws Exception{
        String url = "http://localhost:8080/v1/goods/{goodsNo}";
        ResponseEntity<Map> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, null, Map.class, "240501100002");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().get("resultCode")).isEqualTo("0000");
    }
}
