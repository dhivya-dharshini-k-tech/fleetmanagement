package com.example.fleet_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fleet_management.entity.TireProduct;
import com.example.fleet_management.repository.TireProductRepository;

@Service
public class TireProductService {

    private final TireProductRepository tireProductRepository;

    public TireProductService(
            TireProductRepository tireProductRepository) {

        this.tireProductRepository = tireProductRepository;
    }

    public TireProduct createProduct(TireProduct product) {
        return tireProductRepository.save(product);
    }

    public List<TireProduct> getAllProducts() {
        return tireProductRepository.findAll();
    }

    public TireProduct getProductById(Long id) {
        return tireProductRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tire product not found"));
    }

    public TireProduct updateProduct(
            Long id,
            TireProduct product) {

        TireProduct existing =
                getProductById(id);

        existing.setProductName(product.getProductName());
        existing.setProductType(product.getProductType());
        existing.setManufacturer(product.getManufacturer());
        existing.setUnitPrice(product.getUnitPrice());
        existing.setStockQuantity(product.getStockQuantity());

        return tireProductRepository.save(existing);
    }
}