package com.cosmetics.vendor.service.impl;

import com.cosmetics.vendor.VendorMgmt;
import com.cosmetics.vendor.repository.VendorRepository;
import com.cosmetics.vendor.service.VendorService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository = VendorRepository.getInstance();

    @Override
    public VendorMgmt findVendor(String vendorId) {
        return vendorRepository.findVendor(vendorId);
    }

    @Override
    public void save(VendorMgmt vendorMgmt) {
        vendorRepository.save(vendorMgmt);
    }

    @Override
    public void deleteVendor(String vendorId, Map<String, String> resultMap) {
        vendorRepository.deleteVendor(vendorId, resultMap);
    }
}
