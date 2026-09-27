package com.example.fleet_management.service;

import com.example.fleet_management.entity.Budget;
import com.example.fleet_management.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public Budget createBudget(Budget budget) {

        budget.setRemainingAmount(
                budget.getAllocatedAmount()
                        - budget.getActualExpense()
        );

        return budgetRepository.save(budget);
    }

    public Budget updateActualExpense(Long id, double expense) {

        Budget budget = budgetRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Budget not found"));

        budget.setActualExpense(expense);

        budget.setRemainingAmount(
                budget.getAllocatedAmount() - expense
        );

        return budgetRepository.save(budget);
    }

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Budget getBudgetById(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Budget not found"));
    }
}