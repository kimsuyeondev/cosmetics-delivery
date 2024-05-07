package com.cosmetics.domain.member.repository;

import com.cosmetics.domain.member.dto.MemberManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberManagement, Long> {

    MemberManagement findByMemberId(Long memberId);

    MemberManagement save(MemberManagement memberManagement);

    void deleteByMemberId(Long memberId);
}
