package com.cosmetics.domain.vendor.repository;

import com.cosmetics.domain.vendor.dto.VendorManagement;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/*
    API Spec 및 컨트롤러 작성을 위해 임시적으로 싱글톤 작성하였습니다.
* */
@Repository
public class VendorRepository {
    private static Map<String, VendorManagement> vendorMap = new ConcurrentHashMap<>();

    private static AtomicLong sequence = new AtomicLong();

    private static final VendorRepository instance = new VendorRepository();

    public static VendorRepository getInstance() {
        return instance;
    }

    public VendorManagement save(VendorManagement vendor) {
        String vendorId = "lv20240000" + sequence.incrementAndGet();
        vendor.setVendorId(vendorId);
        vendorMap.put(vendorId, vendor);
        return vendor;
    }

    public VendorManagement findVendor(String vendorId) {
        return vendorMap.get(vendorId);
    }

    public VendorManagement deleteVendor(String vendorId) {
        VendorManagement removeGoods = vendorMap.get(vendorId);
        vendorMap.remove(vendorId);
        return removeGoods;
    }

}
