package com.PriceComparatorBackend.PriceComparatorBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PriceComparatorBackend.PriceComparatorBackend.model.PriceHistoryPoint;
import com.PriceComparatorBackend.PriceComparatorBackend.model.PriceHistoryResponse;
import com.PriceComparatorBackend.PriceComparatorBackend.model.PriceStatistics;
import com.PriceComparatorBackend.PriceComparatorBackend.model.ProductFilter;
import com.PriceComparatorBackend.PriceComparatorBackend.model.Product;
import com.PriceComparatorBackend.PriceComparatorBackend.repository.PriceHistoryRepository;
import com.PriceComparatorBackend.PriceComparatorBackend.repository.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PriceHistoryService {
        private final PriceHistoryRepository priceHistoryRepository;
        private final ProductRepository productRepository;
        
        @Autowired
        public PriceHistoryService(PriceHistoryRepository priceHistoryRepository, ProductRepository productRepository) {
                this.priceHistoryRepository = priceHistoryRepository;
                this.productRepository = productRepository;
        }

        public List<PriceHistoryResponse> getPriceHistory(ProductFilter filter) {
                List<Product> products = productRepository.getAllProducts();
                if (products.size() == 0 || products == null) {
                        return null;
                }

                List<Product> filteredProducts = productRepository.filterProducts(products, filter);
                if (filteredProducts.isEmpty()) {
                        return null;
                }

                List<PriceHistoryResponse> response = new ArrayList<>();
                for (Product product : filteredProducts) {
                        List<PriceHistoryPoint> pricePoints = new ArrayList<>();
                        for (PriceHistoryPoint pricePoint : priceHistoryRepository
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
