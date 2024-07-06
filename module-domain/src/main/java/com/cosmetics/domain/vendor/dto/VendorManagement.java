package com.cosmetics.domain.vendor.dto;

import com.cosmetics.domain.vendor.entity.VendorManagementEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VendorManagement {

    @NotBlank
    private String vendorNm;
    @NotBlank
    private String postNo;
    @NotBlank
    private String addr;

    private String addrDetail;
    @NotBlank
    private String bizNo;
    private Long vendorId;

    @Builder
    public VendorManagement(Long vendorId, String vendorNm, String postNo, String addr, String addrDetail, String bizNo, String resultCode, String resultMsg) {
        this.vendorId = vendorId;
        this.vendorNm = vendorNm;
        this.postNo = postNo;
        this.addr = addr;
        this.addrDetail = addrDetail;
        this.bizNo = bizNo;
    }

    public VendorManagementEntity toEntity() {
        return VendorManagementEntity.builder()
                .vendorNm(vendorNm)
                .postNo(postNo)
                .addr(addr)
                .addrDetail(addrDetail)
                .bizNo(bizNo)
                .build();
    }

    public VendorManagement toDto(VendorManagementEntity vendorManagementEntity) {
        return VendorManagement.builder()
                .vendorId(vendorManagementEntity.getVendorId())
                .vendorNm(vendorManagementEntity.getVendorNm())
                .postNo(vendorManagementEntity.getPostNo())
                .addr(vendorManagementEntity.getAddr())
                .addrDetail(vendorManagementEntity.getAddrDetail())
                .bizNo(vendorManagementEntity.getBizNo())
                .build();
    }

}
