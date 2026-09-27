package com.example.fleet_management.service;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.fleet_management.entity.Account;
import com.example.fleet_management.entity.CustomerInvoice;
import com.example.fleet_management.entity.TireProduct;
import com.example.fleet_management.entity.VendorBill;
import com.example.fleet_management.repository.AccountRepository;
import com.example.fleet_management.repository.CustomerInvoiceRepository;
import com.example.fleet_management.repository.TireProductRepository;
import com.example.fleet_management.repository.VendorBillRepository;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private TireProductRepository tireProductRepository;

    @Mock
    private CustomerInvoiceRepository customerInvoiceRepository;

    @Mock
    private VendorBillRepository vendorBillRepository;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void profitLossSubtractsVendorBillsFromInvoiceRevenue() {
        CustomerInvoice invoice = new CustomerInvoice();
        invoice.setTotalAmount(143000.00);
        VendorBill bill = new VendorBill();
        bill.setAmount(43000.00);
        when(customerInvoiceRepository.findAll()).thenReturn(List.of(invoice));
        when(vendorBillRepository.findAll()).thenReturn(List.of(bill));
        ReportService reportService = createReportService();

        Map<String, Object> report = reportService.getProfitLoss();

        assertEquals(143000.00, report.get("totalRevenue"));
        assertEquals(43000.00, report.get("totalExpenses"));
        assertEquals(100000.00, report.get("netProfit"));
    }

    @Test
    void balanceSheetTotalsAccountTypesAndInventory() {
        Account asset = account("ASSET", 1000.00);
        Account liability = account("LIABILITY", 500.00);
        Account equity = account("EQUITY", 700.00);
        when(accountRepository.findAll()).thenReturn(List.of(asset, liability, equity));
        TireProduct product = new TireProduct();
        product.setUnitPrice(50.00);
        product.setStockQuantity(4);
        when(tireProductRepository.findAll()).thenReturn(List.of(product));

        Map<String, Object> report = createReportService().getBalanceSheet();

        assertEquals(200.00, report.get("fleetTireInventoryValue"));
        assertEquals(1000.00, report.get("accountAssets"));
        assertEquals(1200.00, report.get("totalAssets"));
        assertEquals(500.00, report.get("totalLiabilities"));
        assertEquals(700.00, report.get("totalEquity"));
        assertEquals(1200.00, report.get("totalLiabilitiesAndEquity"));
    }

    private Account account(String type, double balance) {
        Account account = new Account();
        account.setAccountType(type);
        account.setBalance(balance);
        return account;
    }

    private ReportService createReportService() {
        return new ReportService(
                tireProductRepository,
                customerInvoiceRepository,
                mock(com.example.fleet_management.repository.BudgetRepository.class),
                vendorBillRepository,
                accountRepository);
    }
}