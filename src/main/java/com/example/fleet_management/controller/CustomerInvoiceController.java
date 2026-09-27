package com.example.fleet_management.controller;

import com.example.fleet_management.entity.CustomerInvoice;
import com.example.fleet_management.service.CustomerInvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fleet/customer-invoices")
public class CustomerInvoiceController {

    private final CustomerInvoiceService invoiceService;

    public CustomerInvoiceController(
            CustomerInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public CustomerInvoice createInvoice(
            @RequestBody CustomerInvoice invoice) {

        return invoiceService.createInvoice(invoice);
    }

    @GetMapping
    public List<CustomerInvoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{id}")
    public CustomerInvoice getInvoice(
            @PathVariable Long id) {

        return invoiceService.getInvoiceById(id);
    }
}