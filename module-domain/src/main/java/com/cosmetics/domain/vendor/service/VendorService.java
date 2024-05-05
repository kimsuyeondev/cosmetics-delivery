package com.cosmetics.domain.vendor.service;

import com.cosmetics.domain.vendor.dto.VendorManagement;
import org.springframework.stereotype.Service;

@Service
public interface VendorService{

    VendorManagement findVendor(String vendorId);

    VendorManagement save(VendorManagement VendorManagement);

    VendorManagement deleteVendor(String vendorId);
}
