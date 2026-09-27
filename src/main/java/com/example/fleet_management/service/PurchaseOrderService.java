package com.example.fleet_management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fleet_management.entity.PurchaseOrder;
import com.example.fleet_management.repository.PurchaseOrderRepository;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    public PurchaseOrder createPurchaseOrder(PurchaseOrder order) {

        order.setTotalAmount(
                order.getQuantity() * order.getUnitPrice()
        );

        order.setStatus("CREATED");

        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDateTime.now());
        }

        return purchaseOrderRepository.save(order);
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));
    }
}