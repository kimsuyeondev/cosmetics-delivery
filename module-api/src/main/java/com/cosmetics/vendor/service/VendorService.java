package com.cosmetics.vendor.service;

import com.cosmetics.vendor.VendorMgmt;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface VendorService{

    VendorMgmt findVendor(String vendorId);

    void save(VendorMgmt vendorMgmt);

    void deleteVendor(String vendorId, Map<String, String> resultMap);
}
