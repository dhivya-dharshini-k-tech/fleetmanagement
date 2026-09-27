package com.example.fleet_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fleet_management.entity.TireProduct;

public interface TireProductRepository
        extends JpaRepository<TireProduct, Long> {
}