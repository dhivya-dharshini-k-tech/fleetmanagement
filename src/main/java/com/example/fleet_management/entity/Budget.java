package com.example.fleet_management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "budgets")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String analyticAccount;
    private String division;
    private double allocatedAmount;
    private double actualExpense;
    private double remainingAmount;

    public Budget() {
    }

    public Long getId() {
        return id;
    }

    public String getAnalyticAccount() {
        return analyticAccount;
    }

    public void setAnalyticAccount(String analyticAccount) {
        this.analyticAccount = analyticAccount;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public double getAllocatedAmount() {
        return allocatedAmount;
    }

    public void setAllocatedAmount(double allocatedAmount) {
        this.allocatedAmount = allocatedAmount;
    }

    public double getActualExpense() {
        return actualExpense;
    }

    public void setActualExpense(double actualExpense) {
        this.actualExpense = actualExpense;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }
}