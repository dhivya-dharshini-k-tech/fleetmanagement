package com.example.fleet_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fleet_management.entity.VendorBill;

public interface VendorBillRepository extends JpaRepository<VendorBill, Long> {
}