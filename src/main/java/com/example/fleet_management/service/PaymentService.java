package com.example.fleet_management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fleet_management.entity.Payment;
import com.example.fleet_management.entity.VendorBill;
import com.example.fleet_management.repository.PaymentRepository;
import com.example.fleet_management.repository.VendorBillRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final VendorBillRepository vendorBillRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            VendorBillRepository vendorBillRepository) {

        this.paymentRepository = paymentRepository;
        this.vendorBillRepository = vendorBillRepository;
    }

    public Payment makePayment(Long vendorBillId, String paymentMethod) {

        VendorBill bill = vendorBillRepository.findById(vendorBillId)
                .orElseThrow(() -> new RuntimeException("Vendor Bill not found"));

        if ("PAID".equals(bill.getStatus())) {
            throw new RuntimeException("Vendor Bill is already paid");
        }

        Payment payment = new Payment();

        payment.setVendorBillId(bill.getId());
        payment.setAmount(bill.getAmount());
        payment.setPaymentMethod(paymentMethod);
        payment.setStatus("COMPLETED");
        payment.setPaymentDate(LocalDateTime.now());

        bill.setStatus("PAID");
        vendorBillRepository.save(bill);

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {

        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}