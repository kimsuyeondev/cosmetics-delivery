package com.cosmetics.api.member.controller;

import com.cosmetics.domain.member.dto.MemberManagement;
import com.cosmetics.domain.member.service.MemberService;
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
    public MemberManagement findMember(@PathVariable String memberId) {
        return memberService.findMember(memberId);
    }

    @PostMapping
    public MemberManagement registerMember(@RequestBody MemberManagement MemberManagement) {
        return memberService.save(MemberManagement);
    }

}
