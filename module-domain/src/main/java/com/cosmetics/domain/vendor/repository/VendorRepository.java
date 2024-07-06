package com.cosmetics.domain.vendor.repository;

import com.cosmetics.domain.vendor.entity.VendorManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<VendorManagementEntity, Long> {

    Optional<VendorManagementEntity> findByVendorId(Long vendorId);

    VendorManagementEntity save(VendorManagementEntity vendor);

    int deleteByVendorId(Long vendorId);

}
