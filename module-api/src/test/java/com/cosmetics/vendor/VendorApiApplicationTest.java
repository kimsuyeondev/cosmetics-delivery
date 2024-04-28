package com.cosmetics.vendor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VendorApiApplicationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    VendorMgmt vendorMgmt = new VendorMgmt();

    @BeforeEach
    public void setUp() throws Exception{
        vendorMgmt.setVendorNm("달바");
        vendorMgmt.setAddr("서울 여의도동 60");
        vendorMgmt.setAddrDetail("63빌딩");
        vendorMgmt.setBizNo("111-111-111");
        vendorMgmt.setPostNo("101-000");
    }

    @DisplayName("업체등록")
    @Test
    @Order(1)
    public void 업체등록() throws Exception{
        String url = "http://localhost:8080/v1/vendor";
        System.out.println("1");
        ResponseEntity<VendorMgmt> responseEntity = testRestTemplate.postForEntity(url, vendorMgmt, VendorMgmt.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getVendorId()).isEqualTo("lv202400001");

    }

    @DisplayName("업체찾기")
    @Test
    @Order(2)
    public void 업체찾기() throws Exception{
        System.out.println("2");
        String url = "http://localhost:8080/v1/vendor/{vendorId}";
        ResponseEntity<VendorMgmt> responseEntity = testRestTemplate.getForEntity(url, VendorMgmt.class, "lv202400001");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getVendorNm()).isEqualTo("달바");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseEntity.getBody());
        System.out.println(json);
    }

    @DisplayName("업체삭제")
    @Test
    @Order(3)
    public void 업체삭제() throws Exception{
        System.out.println("3");
        String url = "http://localhost:8080/v1/vendor/{vendorId}";
        ResponseEntity<Map> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, null, Map.class, "lv202400001");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().get("resultCode")).isEqualTo("0000");
    }

}
