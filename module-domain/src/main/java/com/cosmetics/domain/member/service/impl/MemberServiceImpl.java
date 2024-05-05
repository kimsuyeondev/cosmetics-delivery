package com.cosmetics.domain.member.service.impl;

import com.cosmetics.domain.exception.custom.CustomException;
import com.cosmetics.domain.exception.error.MemberErrorManagement;
import com.cosmetics.domain.member.dto.MemberManagement;
import com.cosmetics.domain.member.service.MemberService;
import com.cosmetics.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public MemberManagement findMember(String memberId) {
        return memberRepository.findMember(memberId);
    }

    @Override
    public MemberManagement save(MemberManagement memberManagement) {
        MemberManagement resultMemberManagement = memberRepository.save(memberManagement);

        log.error(resultMemberManagement.getMemberId());

        if (resultMemberManagement.getMemberId() != null) {
            resultMemberManagement.updateSuccess("0000", "등록성공");
        } else {
            throw new CustomException(MemberErrorManagement.MEMBER_SAVE_ERROR);
        }
        return resultMemberManagement;
    }
}
