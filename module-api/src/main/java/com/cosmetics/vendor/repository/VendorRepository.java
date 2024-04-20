package com.cosmetics.vendor.repository;

import com.cosmetics.vendor.VendorMgmt;
import java.util.HashMap;
import java.util.Map;

/*
    API Spec 및 컨트롤러 작성을 위해 임시적으로 싱글톤 작성하였습니다.
* */

public class VendorRepository {
    private static Map<String, VendorMgmt> vendorMap = new HashMap<>();

    private static long sequence = 0L;

    private static final VendorRepository instance = new VendorRepository();

    public static VendorRepository getInstance(){
        return instance;
    }

    public VendorMgmt save(VendorMgmt vendor){
        String vendorId = "lv20240000" + ++sequence;
        vendor.setVendorId(vendorId);
        vendorMap.put(vendorId, vendor);
        return vendor;
    }

    public VendorMgmt findVendor(String vendorId){
        return vendorMap.get(vendorId);
    };

    public void deleteVendor(String vendorId){
        vendorMap.remove(vendorId);
    }

    public void deleteVendors(){
        vendorMap = new HashMap<>();
        sequence = 0L;
    }

}
