package com.example.fleet_management.controller;

import com.example.fleet_management.entity.MaintenanceAlert;
import com.example.fleet_management.service.MaintenanceAlertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fleet/maintenance-alerts")
public class MaintenanceAlertController {

    private final MaintenanceAlertService alertService;

    public MaintenanceAlertController(
            MaintenanceAlertService alertService) {

        this.alertService = alertService;
    }

    @GetMapping
    public List<MaintenanceAlert> getAllAlerts() {
        return alertService.getAllAlerts();
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<MaintenanceAlert> getAlertsByVehicle(
            @PathVariable Long vehicleId) {

        return alertService.getAlertsByVehicle(vehicleId);
    }
}