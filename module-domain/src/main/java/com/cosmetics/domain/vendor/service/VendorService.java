package com.cosmetics.domain.vendor.service;

import com.cosmetics.domain.vendor.dto.VendorManagement;
import com.cosmetics.domain.vendor.entity.VendorManagementEntity;
import com.cosmetics.domain.vendor.repository.VendorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VendorService {

    private final VendorRepository vendorRepository;

    @Transactional
    public VendorManagement findVendor(Long vendorId) {
        VendorManagementEntity vendorManagementEntity = vendorRepository.findByVendorId(vendorId).orElseThrow(() -> new IllegalArgumentException("업체 정보가 존재하지 않습니다."));
        VendorManagement vendorManagement = new VendorManagement();
        return vendorManagement.toDto(vendorManagementEntity);
    }

    @Transactional
    public VendorManagement save(VendorManagement vendorManagement) {
        VendorManagementEntity vendorManagementEntity = vendorRepository.save(vendorManagement.toEntity());
        VendorManagement resultVendorManagement = new VendorManagement();
        return resultVendorManagement.toDto(vendorManagementEntity);
    }

    @Transactional
    public VendorManagement deleteVendor(Long vendorId) {
        VendorManagementEntity vendorManagementEntity = vendorRepository.findByVendorId(vendorId).orElseThrow(() -> new IllegalArgumentException("업체 정보가 존재하지 않습니다."));
        int deleteCnt = vendorRepository.deleteByVendorId(vendorId);
        VendorManagement resultVendorManagement = new VendorManagement();
        return resultVendorManagement.toDto(vendorManagementEntity);
    }
}
