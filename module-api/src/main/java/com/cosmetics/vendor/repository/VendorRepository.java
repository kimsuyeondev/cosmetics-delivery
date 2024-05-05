package com.cosmetics.vendor.repository;

import com.cosmetics.vendor.VendorMgmt;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/*
    API Spec 및 컨트롤러 작성을 위해 임시적으로 싱글톤 작성하였습니다.
* */
@Repository
public class VendorRepository {
    private static Map<String, VendorMgmt> vendorMap = new ConcurrentHashMap<>();

    private static AtomicLong sequence = new AtomicLong();

    private static final VendorRepository instance = new VendorRepository();

    public static VendorRepository getInstance(){
        return instance;
    }

    public VendorMgmt save(VendorMgmt vendor){
        String vendorId = "lv20240000" + sequence.incrementAndGet();
        vendor.setVendorId(vendorId);
        vendorMap.put(vendorId, vendor);
        return vendor;
    }

    public VendorMgmt findVendor(String vendorId){
        return vendorMap.get(vendorId);
    };

    public void deleteVendor(String vendorId, Map<String, String> resultMap){
        if(!vendorMap.containsKey(vendorId)){
            resultMap.put("resultCode", "-0001");
            resultMap.put("retMsg", "존재하지 않는 업체입니다.");
            return;
        }

        vendorMap.remove(vendorId);

        if(vendorMap.containsKey(vendorId)){
            resultMap.put("resultCode", "-1111");
            resultMap.put("retMsg", "삭제실패");
        }else{
            resultMap.put("resultCode", "0000");
            resultMap.put("retMsg", "삭제성공");
        }
    }

}
