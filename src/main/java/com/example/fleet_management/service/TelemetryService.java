package com.example.fleet_management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fleet_management.entity.Telemetry;
import com.example.fleet_management.repository.TelemetryRepository;

@Service
public class TelemetryService {

    private final TelemetryRepository telemetryRepository;

    private final MaintenanceAlertService maintenanceAlertService;

    public TelemetryService(
            TelemetryRepository telemetryRepository,
            MaintenanceAlertService maintenanceAlertService) {

        this.telemetryRepository = telemetryRepository;
        this.maintenanceAlertService = maintenanceAlertService;
    }

    public Telemetry saveTelemetry(Telemetry telemetry) {

        if (telemetry.getRecordedAt() == null) {
            telemetry.setRecordedAt(LocalDateTime.now());
        }

        Telemetry savedTelemetry =
                telemetryRepository.save(telemetry);

        // MAIN BUSINESS RULE
        if (telemetry.getPsi() < 28) {

            maintenanceAlertService.createAlert(
                    telemetry.getVehicleId(),
                    telemetry.getPsi()
            );
        }

        return savedTelemetry;
    }

    public List<Telemetry> getAllTelemetry() {
        return telemetryRepository.findAll();
    }

    public List<Telemetry> getTelemetryByVehicle(
            Long vehicleId) {

        return telemetryRepository.findByVehicleId(vehicleId);
    }
}