package com.example.fleet_management.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fleet_management.entity.TireProduct;
import com.example.fleet_management.service.TireProductService;

@RestController
@RequestMapping("/api/fleet/products")
public class TireProductController {

    private final TireProductService tireProductService;

    public TireProductController(
            TireProductService tireProductService) {

        this.tireProductService = tireProductService;
    }

    @PostMapping
    public TireProduct createProduct(
            @RequestBody TireProduct product) {

        return tireProductService.createProduct(product);
    }

    @GetMapping
    public List<TireProduct> getAllProducts() {

        return tireProductService.getAllProducts();
    }

    @GetMapping("/{id}")
    public TireProduct getProduct(
            @PathVariable Long id) {

        return tireProductService.getProductById(id);
    }

    @PutMapping("/{id}")
    public TireProduct updateProduct(
            @PathVariable Long id,
            @RequestBody TireProduct product) {

        return tireProductService.updateProduct(id, product);
    }
}