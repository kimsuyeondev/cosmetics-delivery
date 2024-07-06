package com.cosmetics.domain.vendor.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "pv_vend")
public class VendorManagementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long vendorId;

    private String vendorNm;
    private String postNo;
    private String addr;
    private String addrDetail;
    private String bizNo;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime insertDtime;

    @LastModifiedDate
    private LocalDateTime updateDtime;

    @Builder
    public VendorManagementEntity(String vendorNm, String postNo, String addr, String addrDetail, String bizNo) {
        this.vendorNm = vendorNm;
        this.postNo = postNo;
        this.addr = addr;
        this.addrDetail = addrDetail;
        this.bizNo = bizNo;
    }
}
