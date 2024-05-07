package com.cosmetics.domain.member.service;

import com.cosmetics.domain.member.dto.MemberManagement;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    MemberManagement findMember(Long memberId);

    MemberManagement save(MemberManagement MemberManagement);

    void deleteMember(Long memberId);
}
