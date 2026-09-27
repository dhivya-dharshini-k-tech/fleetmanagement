package com.example.fleet_management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fleet_management.entity.PurchaseOrder;
import com.example.fleet_management.entity.VendorBill;
import com.example.fleet_management.repository.PurchaseOrderRepository;
import com.example.fleet_management.repository.VendorBillRepository;

@Service
public class VendorBillService {

    private final VendorBillRepository vendorBillRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public VendorBillService(
            VendorBillRepository vendorBillRepository,
            PurchaseOrderRepository purchaseOrderRepository) {

        this.vendorBillRepository = vendorBillRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public VendorBill createVendorBill(Long purchaseOrderId) {

        PurchaseOrder order = purchaseOrderRepository.findById(purchaseOrderId)
                .orElseThrow(() ->
                        new RuntimeException("Purchase Order not found"));

        VendorBill bill = new VendorBill();

        bill.setPurchaseOrderId(order.getId());
        bill.setSupplierId(order.getSupplierId());
        bill.setAmount(order.getTotalAmount());
        bill.setStatus("UNPAID");
        bill.setBillDate(LocalDateTime.now());

        return vendorBillRepository.save(bill);
    }

    public List<VendorBill> getAllBills() {
        return vendorBillRepository.findAll();
    }

    public VendorBill getBillById(Long id) {
        return vendorBillRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vendor Bill not found"));
    }
}