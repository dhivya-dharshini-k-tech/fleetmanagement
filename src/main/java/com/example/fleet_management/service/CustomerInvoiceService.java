package com.example.fleet_management.service;

import com.example.fleet_management.entity.CustomerInvoice;
import com.example.fleet_management.repository.CustomerInvoiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerInvoiceService {

    private final CustomerInvoiceRepository invoiceRepository;

    public CustomerInvoiceService(
            CustomerInvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public CustomerInvoice createInvoice(CustomerInvoice invoice) {

        invoice.setTotalAmount(
                invoice.getQuantity() * invoice.getUnitPrice()
        );

        invoice.setStatus("UNPAID");

        if (invoice.getInvoiceDate() == null) {
            invoice.setInvoiceDate(LocalDateTime.now());
        }

        return invoiceRepository.save(invoice);
    }

    public List<CustomerInvoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public CustomerInvoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer Invoice not found"));
    }
}