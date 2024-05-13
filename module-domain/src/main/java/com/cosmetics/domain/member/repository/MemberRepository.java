package com.cosmetics.domain.member.repository;

import com.cosmetics.domain.member.dto.MemberManagement;
import com.cosmetics.domain.member.entity.MemberManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberManagementEntity, Long> {

    MemberManagementEntity findByMemberId(Long memberId);

    MemberManagementEntity save(MemberManagementEntity memberManagementEntity);

    Long deleteByMemberId(Long memberId);
}
