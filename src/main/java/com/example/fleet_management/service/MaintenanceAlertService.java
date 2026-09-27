package com.example.fleet_management.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fleet_management.entity.MaintenanceAlert;
import com.example.fleet_management.entity.TireProduct;
import com.example.fleet_management.repository.MaintenanceAlertRepository;
import com.example.fleet_management.repository.TireProductRepository;

@Service
public class MaintenanceAlertService {

    private final MaintenanceAlertRepository alertRepository;
    private final TireProductRepository tireProductRepository;

    public MaintenanceAlertService(
            MaintenanceAlertRepository alertRepository,
            TireProductRepository tireProductRepository) {

        this.alertRepository = alertRepository;
        this.tireProductRepository = tireProductRepository;
    }

    public MaintenanceAlert createAlert(Long vehicleId, double psi) {

        MaintenanceAlert alert = new MaintenanceAlert();

        alert.setVehicleId(vehicleId);
        alert.setPsi(psi);
        alert.setAlertType("LOW_TIRE_PRESSURE");
        alert.setStatus("PENDING");
        alert.setCreatedAt(LocalDateTime.now());

        // Find the first available tire product
        List<TireProduct> products = tireProductRepository.findAll();

        if (!products.isEmpty()) {

            TireProduct tire = products.get(0);

            if (tire.getStockQuantity() > 0) {

                // Reduce tire stock by 1
                tire.setStockQuantity(tire.getStockQuantity() - 1);
                tireProductRepository.save(tire);

                // Mark maintenance as servicing
                alert.setStatus("SERVICING");
            }
        }

        return alertRepository.save(alert);
    }

    public List<MaintenanceAlert> getAllAlerts() {
        return alertRepository.findAll();
    }

    public List<MaintenanceAlert> getAlertsByVehicle(Long vehicleId) {
        return alertRepository.findByVehicleId(vehicleId);
    }
}