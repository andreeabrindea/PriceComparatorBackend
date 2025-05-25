package com.PriceComparatorBackend.PriceComparatorBackend.model;

import java.time.LocalDate;

public class PriceStatistics {
    private Double currentPrice;
    private Double minPrice;
    private Double maxPrice;
    private Double averagePrice;
    private LocalDate minPriceDate;
    private LocalDate maxPriceDate;

    public PriceStatistics() {
    }

    public PriceStatistics(Double currentPrice, Double minPrice, Double maxPrice, Double averagePrice,
            LocalDate minPriceDate, LocalDate maxPriceDate) {
        this.currentPrice = currentPrice;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.averagePrice = averagePrice;
        this.minPriceDate = minPriceDate;
        this.maxPriceDate = maxPriceDate;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public LocalDate getMinPriceDate() {
        return minPriceDate;
    }

    public void setMinPriceDate(LocalDate minPriceDate) {
        this.minPriceDate = minPriceDate;
    }

    public LocalDate getMaxPriceDate() {
        return maxPriceDate;
    }

    public void setMaxPriceDate(LocalDate maxPriceDate) {
        this.maxPriceDate = maxPriceDate;
    }
}
