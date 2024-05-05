package com.cosmetics.member.service.impl;

import com.cosmetics.goods.repository.GoodsRepository;
import com.cosmetics.member.MemberMgmt;
import com.cosmetics.member.repository.MemberRepository;
import com.cosmetics.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    public MemberMgmt findMember(String memberId) {
        return memberRepository.findMember(memberId);
    }

    @Override
    public void save(MemberMgmt memberMgmt) {
        memberRepository.save(memberMgmt);
    }
}
