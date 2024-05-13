package com.cosmetics.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberManagementResponse {
    private Long memberId;
    private String memberNm;
    private String cellNo;
    private String skinType;
    private String age;
    private String addr;
    private String addrDetail;
    private String resultCode;
    private String resultMsg;

    @Builder
    public MemberManagementResponse(Long memberId,
                                    String memberNm,
                                    String cellNo,
                                    String skinType,
                                    String age,
                                    String addr,
                                    String addrDetail,
                                    String resultCode,
                                    String resultMsg) {
        this.memberId = memberId;
        this.memberNm = memberNm;
        this.cellNo = cellNo;
        this.skinType = skinType;
        this.age = age;
        this.addr = addr;
        this.addrDetail = addrDetail;
    }

    public static MemberManagementResponse toResponse(MemberManagement memberManagement){
        return MemberManagementResponse.builder()
                .memberId(memberManagement.getMemberId())
                .memberNm(memberManagement.getMemberNm())
                .cellNo(memberManagement.getCellNo())
                .skinType(memberManagement.getSkinType())
                .age(memberManagement.getAge())
                .addr(memberManagement.getAddr())
                .addrDetail(memberManagement.getAddrDetail())
                .build();
    }

    public void updateResponse(String resultCode, String resultMsg){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }
}
