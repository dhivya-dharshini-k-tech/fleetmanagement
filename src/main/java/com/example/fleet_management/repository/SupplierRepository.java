package com.example.fleet_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fleet_management.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}