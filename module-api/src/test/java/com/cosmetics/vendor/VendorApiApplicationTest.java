package com.cosmetics.vendor;

import com.cosmetics.domain.vendor.dto.VendorManagement;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class VendorApiApplicationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    VendorManagement vendorManagement = new VendorManagement();

    @BeforeEach
    public void setUp() throws Exception {
        vendorManagement.setVendorNm("달바");
        vendorManagement.setAddr("서울 여의도동 60");
        vendorManagement.setAddrDetail("63빌딩");
        vendorManagement.setBizNo("111-111-111");
        vendorManagement.setPostNo("101-000");
    }

    @DisplayName("업체등록")
    @Test
    @Order(1)
    public void 업체등록() throws Exception {
        String url = "http://localhost:" + port + "/v1/vendor";
        ResponseEntity<VendorManagement> responseEntity = testRestTemplate.postForEntity(url, vendorManagement, VendorManagement.class);
        VendorManagement responseBody = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getVendorId()).isNotBlank();
        log.error("vendId = {}", responseBody.getVendorId());
    }

    @DisplayName("업체찾기")
    @Test
    @Order(2)
    public void 업체찾기() throws Exception {
        String url = "http://localhost:" + port + "/v1/vendor/{vendorId}";
        ResponseEntity<VendorManagement> responseEntity = testRestTemplate.getForEntity(url, VendorManagement.class, "lv202400001");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getVendorNm()).isEqualTo("달바");

    }

    @DisplayName("업체삭제")
    @Test
    @Order(3)
    public void 업체삭제() throws Exception {
        String url = "http://localhost:" + port + "/v1/vendor/{vendorId}";
        ResponseEntity<VendorManagement> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, null, VendorManagement.class, "lv202400001");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getVendorId()).isNotBlank();
        log.error("vendId = {}", responseEntity.getBody().getVendorId());
    }

}
