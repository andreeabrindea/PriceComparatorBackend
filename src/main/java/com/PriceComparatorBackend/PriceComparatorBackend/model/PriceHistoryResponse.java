package com.PriceComparatorBackend.PriceComparatorBackend.model;

public class PriceHistoryResponse {
    private String productId;
    private PriceStatistics priceStatistics;

    public PriceHistoryResponse() {
    }

    public PriceHistoryResponse(String productId, PriceStatistics priceStatistics) {
        this.productId = productId;
        this.priceStatistics = priceStatistics;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public PriceStatistics getPriceStatistics() {
        return priceStatistics;
    }

    public void setPriceStatistics(PriceStatistics priceStatistics) {
        this.priceStatistics = priceStatistics;
    }
}
