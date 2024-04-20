package com.cosmetics.vendor.controller;

import com.cosmetics.vendor.VendorMgmt;
import com.cosmetics.vendor.repository.VendorRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/vendor")
public class VendorController {

    /** API Spec 및 컨트롤러 작성을 위해 임시적으로 싱글톤 작성하였습니다. */
    VendorRepository vendorRepository = VendorRepository.getInstance();

    @GetMapping(value = "/{vendorId}")
    public ResponseEntity<VendorMgmt> findVendor(@PathVariable String vendorId) {
        System.out.println(vendorId);
        VendorMgmt vendorRequest = vendorRepository.findVendor(vendorId);
        return new ResponseEntity<>(vendorRequest, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> registerVendor(@RequestBody VendorMgmt vendorMgmt) {
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("resultCode","0000");
        vendorRepository.save(vendorMgmt);
        resultMap.put("vendorId", vendorMgmt.getVendorId());

        System.out.println(vendorMgmt.getPostNo());
        return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
    }

    /* 틀만 작성 */
    @PutMapping
    public ResponseEntity<Void> updateVendor(@RequestBody VendorMgmt vendorMgmt) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<String>> findVendors() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{vendorId}")
    public ResponseEntity<Void> deleteVendor(@PathVariable String vendorId) {
        vendorRepository.deleteVendor(vendorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * VendorApiApplication 테스트 시 초기화가 안되서 추가함..
     * */
    @DeleteMapping
    public ResponseEntity<Void> deleteVendors() {
        vendorRepository.deleteVendors();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
