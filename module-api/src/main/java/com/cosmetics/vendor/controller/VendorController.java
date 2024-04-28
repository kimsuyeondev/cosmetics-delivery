package com.cosmetics.vendor.controller;

import com.cosmetics.vendor.VendorMgmt;
import com.cosmetics.vendor.repository.VendorRepository;
import com.cosmetics.vendor.service.VendorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/vendor")
//@RequiredArgsConstructor
@Slf4j
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping(value = "/{vendorId}")
    public ResponseEntity<VendorMgmt> findVendor(@PathVariable String vendorId) {
        System.out.println(vendorId);
        VendorMgmt vendorRequest = vendorService.findVendor(vendorId);
        return new ResponseEntity<>(vendorRequest, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String,String>> registerVendor(@RequestBody VendorMgmt vendorMgmt) {
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("resultCode","0000");
        vendorService.save(vendorMgmt);
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
    public ResponseEntity<Map<String,String>> deleteVendor(@PathVariable String vendorId) {
        Map<String,String> resultMap = new HashMap<>();
        vendorService.deleteVendor(vendorId, resultMap);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

}
