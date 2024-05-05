package com.cosmetics.domain.member.service;

import com.cosmetics.domain.member.dto.MemberManagement;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    MemberManagement findMember(String memberId);
    MemberManagement save(MemberManagement MemberManagement);
}
