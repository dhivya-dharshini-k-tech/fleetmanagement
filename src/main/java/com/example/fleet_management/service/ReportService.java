package com.example.fleet_management.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.fleet_management.entity.Account;
import com.example.fleet_management.entity.Budget;
import com.example.fleet_management.entity.CustomerInvoice;
import com.example.fleet_management.entity.TireProduct;
import com.example.fleet_management.entity.VendorBill;
import com.example.fleet_management.repository.AccountRepository;
import com.example.fleet_management.repository.BudgetRepository;
import com.example.fleet_management.repository.CustomerInvoiceRepository;
import com.example.fleet_management.repository.TireProductRepository;
import com.example.fleet_management.repository.VendorBillRepository;

@Service
public class ReportService {

    private final TireProductRepository tireProductRepository;
    private final CustomerInvoiceRepository customerInvoiceRepository;
    private final BudgetRepository budgetRepository;
    private final VendorBillRepository vendorBillRepository;
    private final AccountRepository accountRepository;

    public ReportService(
            TireProductRepository tireProductRepository,
            CustomerInvoiceRepository customerInvoiceRepository,
            BudgetRepository budgetRepository,
            VendorBillRepository vendorBillRepository,
            AccountRepository accountRepository) {

        this.tireProductRepository = tireProductRepository;
        this.customerInvoiceRepository = customerInvoiceRepository;
        this.budgetRepository = budgetRepository;
        this.vendorBillRepository = vendorBillRepository;
        this.accountRepository = accountRepository;
    }

    public Map<String, Object> getBalanceSheet() {

        List<TireProduct> products = tireProductRepository.findAll();

        double inventoryValue = 0;

        for (TireProduct product : products) {
            inventoryValue +=
                    product.getUnitPrice() * product.getStockQuantity();
        }

        double accountAssets = 0;
        double totalLiabilities = 0;
        double totalEquity = 0;
        for (Account account : accountRepository.findAll()) {
            if ("ASSET".equalsIgnoreCase(account.getAccountType())) {
                accountAssets += account.getBalance();
            } else if ("LIABILITY".equalsIgnoreCase(account.getAccountType())) {
                totalLiabilities += account.getBalance();
            } else if ("EQUITY".equalsIgnoreCase(account.getAccountType())) {
                totalEquity += account.getBalance();
            }
        }

        double totalAssets = accountAssets + inventoryValue;
        Map<String, Object> report = new LinkedHashMap<>();

        report.put("reportName", "Balance Sheet");
        report.put("fleetTireInventoryValue", inventoryValue);
        report.put("accountAssets", accountAssets);
        report.put("totalAssets", totalAssets);
        report.put("totalLiabilities", totalLiabilities);
        report.put("totalEquity", totalEquity);
        report.put("totalLiabilitiesAndEquity", totalLiabilities + totalEquity);

        return report;
    }

    public Map<String, Object> getProfitLoss() {

        List<CustomerInvoice> invoices =
                customerInvoiceRepository.findAll();

        double totalRevenue = 0;

        for (CustomerInvoice invoice : invoices) {
            totalRevenue += invoice.getTotalAmount();
        }

        List<VendorBill> vendorBills = vendorBillRepository.findAll();
        double totalExpenses = 0;
        for (VendorBill bill : vendorBills) {
            totalExpenses += bill.getAmount();
        }

        Map<String, Object> report = new HashMap<>();

        report.put("reportName", "Profit & Loss");
        report.put("totalRevenue", totalRevenue);
        report.put("totalExpenses", totalExpenses);
        report.put("netProfit", totalRevenue - totalExpenses);

        return report;
    }

    public List<Budget> getBudgetReport() {
        return budgetRepository.findAll();
    }
}