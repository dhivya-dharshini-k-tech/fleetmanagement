package com.example.fleet_management.controller;

import com.example.fleet_management.entity.Budget;
import com.example.fleet_management.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/fleet/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/balance-sheet")
    public Map<String, Object> getBalanceSheet() {
        return reportService.getBalanceSheet();
    }

    @GetMapping("/profit-loss")
    public Map<String, Object> getProfitLoss() {
        return reportService.getProfitLoss();
    }

    @GetMapping("/budget")
    public List<Budget> getBudgetReport() {
        return reportService.getBudgetReport();
    }
}