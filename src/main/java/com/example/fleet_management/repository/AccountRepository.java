package com.example.fleet_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fleet_management.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}