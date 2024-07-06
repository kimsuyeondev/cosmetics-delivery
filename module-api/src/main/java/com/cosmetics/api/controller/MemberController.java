package com.cosmetics.api.controller;

import com.cosmetics.domain.member.dto.MemberManagement;
import com.cosmetics.domain.member.dto.MemberManagementRequest;
import com.cosmetics.domain.member.dto.MemberManagementResponse;
import com.cosmetics.domain.member.service.impl.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/{memberId}")
    public MemberManagementResponse findMember(@PathVariable Long memberId) {
        MemberManagement memberManagement = memberService.findMember(memberId);
        MemberManagementResponse memberManagementResponse = MemberManagementResponse.toResponse(memberManagement);
        memberManagementResponse.updateResponse("0000", "조회성공");

        return memberManagementResponse;
    }

    @PostMapping
    public MemberManagementResponse registerMember(@RequestBody MemberManagementRequest memberManagementRequest) {
        MemberManagement memberManagement = memberService.save(memberManagementRequest.toServiceDto());
        MemberManagementResponse memberManagementResponse = MemberManagementResponse.toResponse(memberManagement);
        memberManagementResponse.updateResponse("0000", "등록성공");
        return memberManagementResponse;
    }

}
