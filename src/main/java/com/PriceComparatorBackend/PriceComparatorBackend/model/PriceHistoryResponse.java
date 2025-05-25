package com.PriceComparatorBackend.PriceComparatorBackend.model;

import java.util.List;

public class PriceHistoryResponse {
    private String productId;
    private String productName;
    private List<PriceHistoryPoint> priceHistory;
    private PriceStatistics priceStatistics;

    public PriceHistoryResponse() {
    }

    public PriceHistoryResponse(String productId, String productName, List<PriceHistoryPoint> priceHistory,
            PriceStatistics priceStatistics) {
        this.productId = productId;
        this.productName = productName;
        this.priceHistory = priceHistory;
        this.priceStatistics = priceStatistics;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<PriceHistoryPoint> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(List<PriceHistoryPoint> priceHistory) {
        this.priceHistory = priceHistory;
    }

    public PriceStatistics getPriceStatistics() {
        return priceStatistics;
    }

    public void setPriceStatistics(PriceStatistics priceStatistics) {
        this.priceStatistics = priceStatistics;
    }
}
