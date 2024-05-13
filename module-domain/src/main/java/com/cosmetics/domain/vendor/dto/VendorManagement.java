package com.cosmetics.domain.vendor.dto;

import com.cosmetics.domain.response.ResponseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorManagement extends ResponseDto {

    @NotBlank
    private String vendorNm;

    private String postNo;
    private String addr;
    private String addrDetail;
    private String bizNo;
    private String vendorId;

    public VendorManagement() {
        super();
    }

    @Builder
    public VendorManagement(String vendorNm, String postNo, String addr, String addrDetail, String bizNo, String resultCode, String resultMsg) {
        super(resultCode, resultMsg);
        this.vendorNm = vendorNm;
        this.postNo = postNo;
        this.addr = addr;
        this.addrDetail = addrDetail;
        this.bizNo = bizNo;
    }

}
