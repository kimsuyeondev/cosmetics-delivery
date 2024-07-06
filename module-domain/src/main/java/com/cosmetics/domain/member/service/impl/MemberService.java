package com.cosmetics.domain.member.service.impl;

import com.cosmetics.domain.member.dto.MemberManagement;
import com.cosmetics.domain.member.entity.MemberManagementEntity;
import com.cosmetics.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberManagement findMember(Long memberId) {
        MemberManagementEntity saveEntity = memberRepository.findByMemberId(memberId).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        //entity -> dto
        MemberManagement memberManagement = MemberManagement.toDto(saveEntity);

        //dto -> response
        return memberManagement;
    }

    @Transactional
    public MemberManagement save(MemberManagement memberManagement) {
        //save
        MemberManagementEntity saveEntity = memberRepository.save(memberManagement.toEntity());

        //entity -> dto
        MemberManagement resultMember = MemberManagement.toDto(saveEntity);

        return resultMember;
    }

    @Transactional
    public void deleteMember(Long memberId) {
        try {
            memberRepository.deleteByMemberId(memberId);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("삭제할 회원번호가 존재하지 않습니다.");
        }
    }


}
