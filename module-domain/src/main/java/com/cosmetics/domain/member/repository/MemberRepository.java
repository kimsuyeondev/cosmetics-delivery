package com.cosmetics.domain.member.repository;

import com.cosmetics.domain.member.entity.MemberManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberManagementEntity, Long> {
    Optional<MemberManagementEntity> findByMemberId(Long memberId);

    MemberManagementEntity save(MemberManagementEntity memberManagementEntity);

    Long deleteByMemberId(Long memberId);
}
