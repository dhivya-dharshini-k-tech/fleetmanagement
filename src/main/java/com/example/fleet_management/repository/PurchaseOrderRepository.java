package com.example.fleet_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fleet_management.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
}