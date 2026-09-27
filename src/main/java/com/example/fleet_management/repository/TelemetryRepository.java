package com.example.fleet_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fleet_management.entity.Telemetry;

public interface TelemetryRepository extends JpaRepository<Telemetry, Long> {

    List<Telemetry> findByVehicleId(Long vehicleId);
}