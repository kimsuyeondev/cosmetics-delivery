package com.cosmetics.member.service;

import com.cosmetics.member.MemberMgmt;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    MemberMgmt findMember(String memberId);
    void save(MemberMgmt memberMgmt);
}
