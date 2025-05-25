package com.PriceComparatorBackend.PriceComparatorBackend.controller;

import com.PriceComparatorBackend.PriceComparatorBackend.model.PriceAlert;
import com.PriceComparatorBackend.PriceComparatorBackend.model.PriceAlertRequest;
import com.PriceComparatorBackend.PriceComparatorBackend.service.PriceAlertService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/price-alerts")
@CrossOrigin(origins = "*")
public class PriceAlertController {
    private final PriceAlertService priceAlertService;

    @Autowired
    public PriceAlertController(PriceAlertService priceAlertService) {
        this.priceAlertService = priceAlertService;
    }

    @PostMapping
    public ResponseEntity<PriceAlert> createAlert(@Valid @RequestBody PriceAlertRequest request) {
        try {
            PriceAlert alert = priceAlertService.createAlert(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(alert);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{alertId}")
    public ResponseEntity<Void> deleteAlert(@PathVariable String alertId) {
        try {
            boolean deleted = priceAlertService.deleteAlert(alertId);
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
