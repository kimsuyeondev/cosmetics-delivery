package com.cosmetics.member.controller;

import com.cosmetics.member.MemberMgmt;
import com.cosmetics.member.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/member")
public class MemberController {

    /** API Spec 및 컨트롤러 작성을 위해 임시적으로 싱글톤 작성하였습니다. */
    MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping(value = "/{memberId}")
    public ResponseEntity<MemberMgmt> findMember(@PathVariable String memberId) {
        System.out.println(memberId);
        MemberMgmt memberMgmt = memberRepository.findMember(memberId);
        return new ResponseEntity<>(memberMgmt, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> registerMember(@RequestBody MemberMgmt memberMgmt) {
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("resultCode","0000");
        memberRepository.save(memberMgmt);
        resultMap.put("memberId", memberMgmt.getMemberId());

        System.out.println(memberMgmt.getMemberNm());
        return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
    }

}
