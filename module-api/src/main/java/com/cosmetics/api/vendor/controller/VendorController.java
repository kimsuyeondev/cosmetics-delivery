package com.cosmetics.api.vendor.controller;

import com.cosmetics.domain.vendor.dto.VendorManagement;
import com.cosmetics.domain.vendor.service.VendorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/vendor")
//@RequiredArgsConstructor
@Slf4j
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping(value = "/{vendorId}")
    public ResponseEntity<VendorManagement> findVendor(@PathVariable String vendorId) {
        System.out.println(vendorId);
        VendorManagement vendorRequest = vendorService.findVendor(vendorId);
        return new ResponseEntity<>(vendorRequest, HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorManagement registerVendor(@RequestBody @Valid VendorManagement VendorManagement) {
        return vendorService.save(VendorManagement);
    }

    @PutMapping
    public ResponseEntity<Void> updateVendor(@RequestBody VendorManagement VendorManagement) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<String>> findVendors() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{vendorId}")
    public ResponseEntity<VendorManagement> deleteVendor(@PathVariable String vendorId) {
        VendorManagement vendorManagement = vendorService.deleteVendor(vendorId);
        return new ResponseEntity<>(vendorManagement, HttpStatus.OK);
    }

}
