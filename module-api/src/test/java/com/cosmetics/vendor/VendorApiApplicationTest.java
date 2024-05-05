package com.cosmetics.vendor;

import com.cosmetics.domain.vendor.dto.VendorManagement;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class VendorApiApplicationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    VendorManagement VendorManagement = new VendorManagement();

    @BeforeEach
    public void setUp() throws Exception {
        VendorManagement.setVendorNm("달바");
        VendorManagement.setAddr("서울 여의도동 60");
        VendorManagement.setAddrDetail("63빌딩");
        VendorManagement.setBizNo("111-111-111");
        VendorManagement.setPostNo("101-000");
    }

    @DisplayName("업체등록")
    @Test
    @Order(1)
    public void 업체등록() throws Exception {
        String url = "http://localhost:8080/v1/vendor";
        ResponseEntity<VendorManagement> responseEntity = testRestTemplate.postForEntity(url, VendorManagement, VendorManagement.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getVendorId()).isEqualTo("lv202400001");

    }

    @DisplayName("업체찾기")
    @Test
    @Order(2)
    public void 업체찾기() throws Exception {
        String url = "http://localhost:8080/v1/vendor/{vendorId}";
        ResponseEntity<VendorManagement> responseEntity = testRestTemplate.getForEntity(url, VendorManagement.class, "lv202400001");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getVendorNm()).isEqualTo("달바");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseEntity.getBody());
        System.out.println(json);
    }

    @DisplayName("업체삭제")
    @Test
    @Order(3)
    public void 업체삭제() throws Exception {
        String url = "http://localhost:8080/v1/vendor/{vendorId}";
        ResponseEntity<VendorManagement> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, null, VendorManagement.class, "lv202400001");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getResultCode()).isEqualTo("0000");
    }

}
