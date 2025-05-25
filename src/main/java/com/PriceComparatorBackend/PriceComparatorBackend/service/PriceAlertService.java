package com.PriceComparatorBackend.PriceComparatorBackend.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PriceComparatorBackend.PriceComparatorBackend.model.PriceAlert;
import com.PriceComparatorBackend.PriceComparatorBackend.model.PriceAlertRequest;
import com.PriceComparatorBackend.PriceComparatorBackend.model.Product;
import com.PriceComparatorBackend.PriceComparatorBackend.model.ProductFilter;
import com.PriceComparatorBackend.PriceComparatorBackend.repository.PriceAlertRepository;
import com.PriceComparatorBackend.PriceComparatorBackend.repository.ProductRepository;

@Service
public class PriceAlertService {
    private final PriceAlertRepository priceAlertRepository;
    private final ProductRepository productRepository;

    @Autowired
    public PriceAlertService(PriceAlertRepository priceAlertRepository, ProductRepository productRepository) {
        this.priceAlertRepository = priceAlertRepository;
        this.productRepository = productRepository;
    }

    public PriceAlert createAlert(PriceAlertRequest request) {
        ProductFilter filters = new ProductFilter();
        filters.setBrand(request.getBrand());
        filters.setProductCategory(request.getProductName());
        Product product = productRepository.filterProducts(productRepository.getAllProducts(), new ProductFilter()).get(0);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        
        PriceAlert alert = new PriceAlert(
            request.getAlertId(),
            request.getUserId(),
            request.getTargetPrice(),
            product.getProductName(),
            request.getBrand(),
            LocalDate.now()
        );
        
        return priceAlertRepository.saveAlert(alert);
    }

     public boolean deleteAlert(String alertId) {
        return priceAlertRepository.deleteAlert(alertId);
    }
}
