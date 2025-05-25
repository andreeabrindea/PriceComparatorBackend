package com.PriceComparatorBackend.PriceComparatorBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PriceComparatorBackend.PriceComparatorBackend.model.PriceHistoryPoint;
import com.PriceComparatorBackend.PriceComparatorBackend.model.PriceHistoryResponse;
import com.PriceComparatorBackend.PriceComparatorBackend.model.PriceStatistics;
import com.PriceComparatorBackend.PriceComparatorBackend.model.ProductFilter;
import com.PriceComparatorBackend.PriceComparatorBackend.model.Product;
import com.PriceComparatorBackend.PriceComparatorBackend.repository.DiscountRepository;
import com.PriceComparatorBackend.PriceComparatorBackend.repository.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PriceHistoryService {

        private final ProductRepository productRepository;
        private final DiscountRepository discountRepository;

        @Autowired
        public PriceHistoryService(ProductRepository productRepository, DiscountRepository discountRepository) {
                this.productRepository = productRepository;
                this.discountRepository = discountRepository;
        }

        public List<PriceHistoryResponse> getPriceHistory(ProductFilter filter) {
                List<Product> products = productRepository.getAllProducts();
                if (products.size() == 0 || products == null) {
                        return null;
                }

                List<Product> filteredProducts = filterProducts(products, filter);
                if (filteredProducts.isEmpty()) {
                        return null;
                }

                List<PriceHistoryResponse> response = new ArrayList<>();
                for (Product product : filteredProducts) {
                        List<PriceHistoryPoint> pricePoints = new ArrayList<>();
                        for (PriceHistoryPoint pricePoint : discountRepository
                                        .applyDiscountsAndGetPriceHistoryForProduct(product)) {
                                pricePoints.add(pricePoint);
                        }
                        PriceStatistics statistics = calculateStatistics(pricePoints);
                        response.add(new PriceHistoryResponse(product.getProductId(),
                                        product.getProductName(),
                                        pricePoints,
                                        statistics));
                }

                return response;
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
                                                                        .contains(filters.getProductCategory().toLowerCase());
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

        private PriceStatistics calculateStatistics(List<PriceHistoryPoint> points) {
                if (points.isEmpty()) {
                        return new PriceStatistics();
                }

                List<Double> prices = points.stream()
                                .map(PriceHistoryPoint::getPrice)
                                .collect(Collectors.toList());

                Double currentPrice = points.get(points.size() - 1).getPrice();
                Double averagePrice = prices.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
                Double minPrice = Collections.min(prices);
                Double maxPrice = Collections.max(prices);

                PriceHistoryPoint minPoint = points.stream()
                                .min(Comparator.comparing(PriceHistoryPoint::getPrice))
                                .orElse(points.get(0));

                PriceHistoryPoint maxPoint = points.stream()
                                .max(Comparator.comparing(PriceHistoryPoint::getPrice))
                                .orElse(points.get(0));

                return new PriceStatistics(
                                currentPrice,
                                minPrice,
                                maxPrice,
                                averagePrice,
                                minPoint.getDate(),
                                maxPoint.getDate());
        }
}
