package com.cosmetics.member.repository;

import com.cosmetics.member.MemberMgmt;

import java.util.HashMap;
import java.util.Map;
/*
    API Spec 및 컨트롤러 작성을 위해 임시적으로 싱글톤 작성하였습니다.
* */

public class MemberRepository {
    private static Map<String, MemberMgmt> memberMap = new HashMap<>();

    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    public MemberMgmt save(MemberMgmt memberMgmt){
        String memberId = "20240501100000000" + ++sequence;
        memberMgmt.setMemberId(memberId);
        memberMap.put(memberId, memberMgmt);
        return memberMgmt;
    }

    public MemberMgmt findMember(String memberId){
        return memberMap.get(memberId);
    };

}
