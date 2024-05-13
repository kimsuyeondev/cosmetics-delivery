package com.cosmetics.domain.member.service.impl;

import com.cosmetics.domain.exception.custom.CustomException;
import com.cosmetics.domain.exception.error.MemberErrorManagement;
import com.cosmetics.domain.member.dto.MemberManagement;
import com.cosmetics.domain.member.dto.MemberManagementResponse;
import com.cosmetics.domain.member.entity.MemberManagementEntity;
import com.cosmetics.domain.member.repository.MemberRepository;
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

    public MemberManagementResponse findMember(Long memberId) {
        MemberManagementEntity saveEntity =  memberRepository.findByMemberId(memberId);

        if(saveEntity == null) {
            throw new IllegalArgumentException("회원이 존재하지 않습니다.");
        }
        MemberManagement memberManagement = MemberManagement.toDto(saveEntity);

        return MemberManagementResponse.toResponse(memberManagement);
    }

    public MemberManagementResponse save(MemberManagement memberManagement) {
        //save
        MemberManagementEntity saveEntity =  memberRepository.save(memberManagement.toEntity());

        //entity -> dto
        MemberManagement resultMember = MemberManagement.toDto(saveEntity);

        //serviceDto -> responseDto
        MemberManagementResponse response = MemberManagementResponse.toResponse(resultMember);

        if (resultMember.getMemberId() != null) {
            response.updateResponse("0000", "등록성공");
        } else {
            throw new CustomException(MemberErrorManagement.MEMBER_SAVE_ERROR);
        }
        return response;
    }

    public void deleteMember(Long memberId) {
        try {
            memberRepository.deleteByMemberId(memberId);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("삭제할 회원번호가 존재하지 않습니다.");
        }
    }


}
