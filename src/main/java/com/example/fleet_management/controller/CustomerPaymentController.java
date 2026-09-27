package com.example.fleet_management.controller;

import com.example.fleet_management.entity.CustomerPayment;
import com.example.fleet_management.service.CustomerPaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fleet/customer-payments")
public class CustomerPaymentController {

    private final CustomerPaymentService paymentService;

    public CustomerPaymentController(
            CustomerPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{invoiceId}")
    public CustomerPayment makePayment(
            @PathVariable Long invoiceId,
            @RequestParam String paymentMethod) {

        return paymentService.makePayment(
                invoiceId,
                paymentMethod);
    }

    @GetMapping
    public List<CustomerPayment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    public CustomerPayment getPayment(@PathVariable Long id) {
        return paymentService.getPaymentById(id);
    }
}