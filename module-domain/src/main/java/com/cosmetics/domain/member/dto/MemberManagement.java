package com.cosmetics.domain.member.dto;

import com.cosmetics.domain.member.entity.MemberManagementEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberManagement {
    private Long memberId;

    private String memberNm;

    private String cellNo;

    private String skinType;

    private String age;

    private String addr;
    private String addrDetail;

    @Builder
    public MemberManagement(Long memberId,
                            String memberNm,
                            String cellNo,
                            String skinType,
                            String age,
                            String addr,
                            String addrDetail) {
        this.memberId = memberId;
        this.memberNm = memberNm;
        this.cellNo = cellNo;
        this.skinType = skinType;
        this.age = age;
        this.addr = addr;
        this.addrDetail = addrDetail;
    }

    //엔티티로
    public MemberManagementEntity toEntity() {
        return MemberManagementEntity.builder()
                .memberNm(memberNm)
                .cellNo(cellNo)
                .skinType(skinType)
                .age(age)
                .addr(addr)
                .addrDetail(addrDetail).build();
    }

    public static MemberManagement toDto(MemberManagementEntity memberManagementEntity) {
        return MemberManagement.builder()
                .memberId(memberManagementEntity.getMemberId())
                .memberNm(memberManagementEntity.getMemberNm())
                .cellNo(memberManagementEntity.getCellNo())
                .skinType(memberManagementEntity.getSkinType())
                .age(memberManagementEntity.getAge())
                .addr(memberManagementEntity.getAddr())
                .addrDetail(memberManagementEntity.getAddrDetail())
                .build();
    }


}
