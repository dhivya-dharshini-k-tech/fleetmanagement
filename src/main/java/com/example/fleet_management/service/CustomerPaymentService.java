package com.example.fleet_management.service;

import com.example.fleet_management.entity.CustomerInvoice;
import com.example.fleet_management.entity.CustomerPayment;
import com.example.fleet_management.repository.CustomerInvoiceRepository;
import com.example.fleet_management.repository.CustomerPaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerPaymentService {

    private final CustomerPaymentRepository paymentRepository;
    private final CustomerInvoiceRepository invoiceRepository;

    public CustomerPaymentService(
            CustomerPaymentRepository paymentRepository,
            CustomerInvoiceRepository invoiceRepository) {

        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public CustomerPayment makePayment(
            Long invoiceId,
            String paymentMethod) {

        CustomerInvoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() ->
                        new RuntimeException("Customer Invoice not found"));

        if ("PAID".equals(invoice.getStatus())) {
            throw new RuntimeException("Invoice is already paid");
        }

        CustomerPayment payment = new CustomerPayment();

        payment.setInvoiceId(invoice.getId());
        payment.setAmount(invoice.getTotalAmount());
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus("COMPLETED");
        payment.setPaymentDate(LocalDateTime.now());

        invoice.setStatus("PAID");
        invoiceRepository.save(invoice);

        return paymentRepository.save(payment);
    }

    public List<CustomerPayment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public CustomerPayment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer Payment not found"));
    }
}