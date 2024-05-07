package com.cosmetics.domain.member.service.impl;

import com.cosmetics.domain.exception.custom.CustomException;
import com.cosmetics.domain.exception.error.MemberErrorManagement;
import com.cosmetics.domain.member.dto.MemberManagement;
import com.cosmetics.domain.member.service.MemberService;
import com.cosmetics.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberManagement findMember(Long memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    @Override
    public MemberManagement save(MemberManagement memberManagement) {
        MemberManagement resultMemberManagement = memberRepository.save(memberManagement);

        log.error(String.valueOf(resultMemberManagement.getMemberId()));

        if (resultMemberManagement.getMemberId() != null) {
            resultMemberManagement.updateSuccess("0000", "등록성공");
        } else {
            throw new CustomException(MemberErrorManagement.MEMBER_SAVE_ERROR);
        }

        return resultMemberManagement;
    }

    @Override
    public void deleteMember(Long memberId) {
        try { //확인필요 jpa는 대부분 void로 되있어서 고민이 많아집니다
            memberRepository.deleteByMemberId(memberId);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("삭제할 회원번호가 존재하지 않습니다.");
        }
    }

}
