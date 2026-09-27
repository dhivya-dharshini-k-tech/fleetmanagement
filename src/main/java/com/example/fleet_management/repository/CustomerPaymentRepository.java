package com.example.fleet_management.repository;

import com.example.fleet_management.entity.CustomerPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerPaymentRepository
        extends JpaRepository<CustomerPayment, Long> {
}