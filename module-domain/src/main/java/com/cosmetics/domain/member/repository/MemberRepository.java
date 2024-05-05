package com.cosmetics.domain.member.repository;

import com.cosmetics.domain.member.dto.MemberManagement;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/*
    API Spec 및 컨트롤러 작성을 위해 임시적으로 싱글톤 작성하였습니다.
* */
@Repository
public class MemberRepository {
    private static Map<String, MemberManagement> memberMap = new ConcurrentHashMap<>();

    private static AtomicLong sequence = new AtomicLong();

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    public MemberManagement save(MemberManagement memberManagement){
        String memberId = "20240501100000000" + sequence.getAndIncrement();
        memberManagement.setMemberId(memberId);
        memberMap.put(memberId, memberManagement);
        return memberManagement;
    }

    public MemberManagement findMember(String memberId){
        return memberMap.get(memberId);
    };

}
