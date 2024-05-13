package com.cosmetics.domain.member.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberManagementRequest {

    @NotBlank(message = "이름을 입력해주세요.")
    private String memberNm;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String cellNo;

    private String skinType;

    @Min(12)
    private String age;

    private String addr;
    private String addrDetail;

    @Builder
    public MemberManagementRequest (String memberNm,
                            String cellNo,
                            String skinType,
                            String age,
                            String addr,
                            String addrDetail) {
        this.memberNm = memberNm;
        this.cellNo = cellNo;
        this.skinType = skinType;
        this.age = age;
        this.addr = addr;
        this.addrDetail = addrDetail;
    }

    public MemberManagement toServiceDto() {
        return MemberManagement.builder()
                .memberNm(memberNm)
                .cellNo(cellNo)
                .skinType(skinType)
                .age(age)
                .addr(addr)
                .addrDetail(addrDetail)
                .build();
    }
}
