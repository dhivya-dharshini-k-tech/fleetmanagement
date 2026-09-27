package com.example.fleet_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fleet_management.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}