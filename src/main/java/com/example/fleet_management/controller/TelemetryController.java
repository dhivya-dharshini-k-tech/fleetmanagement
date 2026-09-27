package com.example.fleet_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fleet_management.entity.Telemetry;
import com.example.fleet_management.service.TelemetryService;

@RestController
@RequestMapping("/api/fleet/telemetry")
public class TelemetryController {

    private final TelemetryService telemetryService;

    public TelemetryController(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    @PostMapping
    public Telemetry saveTelemetry(@RequestBody Telemetry telemetry) {
        return telemetryService.saveTelemetry(telemetry);
    }

    @GetMapping
    public List<Telemetry> getAllTelemetry() {
        return telemetryService.getAllTelemetry();
    }

    @GetMapping("/vehicle/{vehicleId}")
    public List<Telemetry> getTelemetryByVehicle(
            @PathVariable Long vehicleId) {

        return telemetryService.getTelemetryByVehicle(vehicleId);
    }
}