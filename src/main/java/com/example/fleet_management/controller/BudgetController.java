package com.example.fleet_management.controller;

import com.example.fleet_management.entity.Budget;
import com.example.fleet_management.service.BudgetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fleet/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetService.createBudget(budget);
    }

    @PutMapping("/{id}/expense")
    public Budget updateExpense(
            @PathVariable Long id,
            @RequestParam double expense) {

        return budgetService.updateActualExpense(id, expense);
    }

    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetService.getAllBudgets();
    }

    @GetMapping("/{id}")
    public Budget getBudget(@PathVariable Long id) {
        return budgetService.getBudgetById(id);
    }
}