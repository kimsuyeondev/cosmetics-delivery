package com.cosmetics.domain.vendor.service.impl;

import com.cosmetics.domain.exception.custom.CustomException;
import com.cosmetics.domain.exception.error.VendorErrorManagement;
import com.cosmetics.domain.vendor.dto.VendorManagement;
import com.cosmetics.domain.vendor.repository.VendorRepository;
import com.cosmetics.domain.vendor.service.VendorService;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository = VendorRepository.getInstance();

    @Override
    public VendorManagement findVendor(String vendorId) {
        return vendorRepository.findVendor(vendorId);
    }

    @Override
    public VendorManagement save(VendorManagement vendorManagement) {
        VendorManagement resultGoodsMgmt = vendorRepository.save(vendorManagement);

        if (resultGoodsMgmt.getVendorId() != null) {
            resultGoodsMgmt.updateSuccess("0000", "등록성공");
        } else {
            throw new CustomException(VendorErrorManagement.VENDOR_ERROR_MANAGEMENT);
        }

        return resultGoodsMgmt;
    }

    @Override
    public VendorManagement deleteVendor(String vendorId) {
        return vendorRepository.deleteVendor(vendorId);
    }
}
