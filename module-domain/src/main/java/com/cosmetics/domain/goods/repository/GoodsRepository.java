package com.cosmetics.domain.goods.repository;

import com.cosmetics.domain.goods.entity.GoodsManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<GoodsManagementEntity, Long> {
    Optional<GoodsManagementEntity> findByGoodsNo(Long goodsNo);

    GoodsManagementEntity save(GoodsManagementEntity goodsManagementEntity);

    long deleteByGoodsNo(Long goodsNo);
}
