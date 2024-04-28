package com.cosmetics.member.controller;

import com.cosmetics.goods.service.GoodsService;
import com.cosmetics.member.MemberMgmt;
import com.cosmetics.member.repository.MemberRepository;
import com.cosmetics.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<MemberMgmt> findMember(@PathVariable String memberId) {
        log.info(memberId);
        MemberMgmt memberMgmt = memberService.findMember(memberId);
        return new ResponseEntity<>(memberMgmt, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> registerMember(@RequestBody MemberMgmt memberMgmt) {
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("resultCode","0000");
        memberService.save(memberMgmt);
        resultMap.put("memberId", memberMgmt.getMemberId());
        return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
    }

}
