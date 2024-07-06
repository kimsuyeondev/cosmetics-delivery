package com.cosmetics.api.application;

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

    @DisplayName("업체등록")
    @Test
    @Order(1)
    public void 업체등록() throws Exception {
        VendorManagement vendorManagement = getVendorManagement();
        String url = "http://localhost:" + port + "/v1/vendor";
        ResponseEntity<VendorManagement> responseEntity = testRestTemplate.postForEntity(url, vendorManagement, VendorManagement.class);
        VendorManagement responseBody = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getVendorId()).isNotNull();
        log.error("vendId = {}", responseBody.getVendorId());
    }

    @DisplayName("업체찾기")
    @Test
    @Order(2)
    public void 업체찾기() throws Exception {
        String url = "http://localhost:" + port + "/v1/vendor/{vendorId}";
        ResponseEntity<VendorManagement> responseEntity = testRestTemplate.getForEntity(url, VendorManagement.class, "1");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getVendorNm()).isEqualTo("달바");

    }

    @DisplayName("업체삭제")
    @Test
    @Order(3)
    public void 업체삭제() throws Exception {
        String url = "http://localhost:" + port + "/v1/vendor/{vendorId}";
        ResponseEntity<VendorManagement> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, null, VendorManagement.class, "1");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getVendorId()).isNotNull();
        log.error("vendId = {}", responseEntity.getBody().getVendorId());
    }

    public VendorManagement getVendorManagement() {
        return  VendorManagement.builder()
                    .vendorNm("달바")
                    .addr("서울 여의도동 60")
                    .addrDetail("63")
                    .bizNo("111-111-111")
                    .postNo("101-111")
                    .build();
    }
}
