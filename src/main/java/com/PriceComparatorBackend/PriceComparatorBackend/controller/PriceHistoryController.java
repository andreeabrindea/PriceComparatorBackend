package com.PriceComparatorBackend.PriceComparatorBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PriceComparatorBackend.PriceComparatorBackend.model.PriceHistoryResponse;
import com.PriceComparatorBackend.PriceComparatorBackend.model.ProductFilter;
import com.PriceComparatorBackend.PriceComparatorBackend.service.PriceHistoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/price-history")
@CrossOrigin(origins = "*")
public class PriceHistoryController {

    private final PriceHistoryService priceHistoryService;

    @Autowired
    public PriceHistoryController(PriceHistoryService priceHistoryService) {
        this.priceHistoryService = priceHistoryService;
    }

    @GetMapping("/product")
    public ResponseEntity<List<PriceHistoryResponse>> getProductPriceHistory(
            @RequestParam(required = false) String store,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand) {
        
        try {
            ProductFilter filter = new ProductFilter();
            filter.setStoreName(store);
            filter.setProductCategory(category);
            filter.setBrand(brand);
            
            List<PriceHistoryResponse> response = priceHistoryService.getPriceHistory(filter);
            
            if (response == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
