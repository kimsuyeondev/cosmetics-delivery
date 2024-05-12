package com.cosmetics.domain.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "MB_MEMBER")
public class MemberManagementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @NotBlank(message = "이름을 입력해주세요.")
    @Column(nullable = false)
    private String memberNm;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Column(nullable = false)
    private String cellNo;

    private String skinType;

    private String age;

    private String addr;
    private String addrDetail;

    @Builder
    public MemberManagementEntity(
                                  String memberNm,
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
}
