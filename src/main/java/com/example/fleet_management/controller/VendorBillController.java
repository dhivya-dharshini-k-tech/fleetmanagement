package com.example.fleet_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fleet_management.entity.VendorBill;
import com.example.fleet_management.service.VendorBillService;

@RestController
@RequestMapping("/api/fleet/vendor-bills")
public class VendorBillController {

    private final VendorBillService vendorBillService;

    public VendorBillController(VendorBillService vendorBillService) {
        this.vendorBillService = vendorBillService;
    }

    @PostMapping("/{purchaseOrderId}")
    public VendorBill createVendorBill(
            @PathVariable Long purchaseOrderId) {

        return vendorBillService.createVendorBill(purchaseOrderId);
    }

    @GetMapping
    public List<VendorBill> getAllBills() {
        return vendorBillService.getAllBills();
    }

    @GetMapping("/{id}")
    public VendorBill getBill(@PathVariable Long id) {
        return vendorBillService.getBillById(id);
    }
}