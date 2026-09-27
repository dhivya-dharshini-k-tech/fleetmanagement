package com.example.fleet_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fleet_management.entity.MaintenanceAlert;

public interface MaintenanceAlertRepository
        extends JpaRepository<MaintenanceAlert, Long> {

    List<MaintenanceAlert> findByVehicleId(Long vehicleId);
}