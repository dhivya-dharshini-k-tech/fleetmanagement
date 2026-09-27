package com.example.fleet_management.repository;

import com.example.fleet_management.entity.CustomerInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerInvoiceRepository
        extends JpaRepository<CustomerInvoice, Long> {
}