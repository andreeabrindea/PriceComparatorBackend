package com.PriceComparatorBackend.PriceComparatorBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PriceComparatorBackend.PriceComparatorBackend.model.PriceHistoryResponse;
import com.PriceComparatorBackend.PriceComparatorBackend.model.ProductFilter;
import com.PriceComparatorBackend.PriceComparatorBackend.model.Product;
import com.PriceComparatorBackend.PriceComparatorBackend.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceHistoryService {

        private final ProductRepository productRepository;

        @Autowired
        public PriceHistoryService(ProductRepository productRepository) {
                this.productRepository = productRepository;
        }

        public List<Product> filterProducts(List<Product> products, ProductFilter filters) {
                return products.stream().filter(
                                p -> {
                                        if (filters.getStoreName() != null) {
                                                if (filters.getProductCategory() == null
                                                                && filters.getBrand() == null) {
                                                        return p.getStoreName().toLowerCase()
                                                                        .contains(filters.getStoreName().toLowerCase());
                                                }

                                                if (filters.getProductCategory() == null) {
                                                        return p.getStoreName().toLowerCase().contains(
                                                                        filters.getStoreName().toLowerCase()) &&
                                                                        p.getBrand().toLowerCase().contains(filters
                                                                                        .getBrand().toLowerCase());
                                                }

                                                if (filters.getBrand() == null) {
                                                        return p.getStoreName().toLowerCase()
                                                                        .contains(filters.getStoreName().toLowerCase())
                                                                        &&
                                                                        p.getProductCategory().toLowerCase().contains(
                                                                                        filters.getProductCategory()
                                                                                                        .toLowerCase());
                                                }
                                                return p.getStoreName().toLowerCase()
                                                                .contains(filters.getStoreName().toLowerCase()) &&
                                                                p.getProductCategory().toLowerCase()
                                                                                .contains(filters.getProductCategory()
                                                                                                .toLowerCase())
                                                                &&
                                                                p.getBrand().toLowerCase().contains(
                                                                                filters.getBrand().toLowerCase());
                                        }

                                        if (filters.getProductCategory() != null) {
                                                if (filters.getBrand() == null) {
                                                        return p.getProductCategory().toLowerCase()
                                                                        .contains(filters.getProductCategory());
                                                }

                                                return p.getProductCategory().toLowerCase()
                                                                .contains(filters.getProductCategory()) &&
                                                                p.getBrand().toLowerCase().contains(
                                                                                filters.getBrand().toLowerCase());
                                        }

                                        if (filters.getBrand() != null) {
                                                return p.getBrand().toLowerCase()
                                                                .contains(filters.getBrand().toLowerCase());
                                        }

                                        return true;
                                }

                ).collect(Collectors.toList());
        }
}
