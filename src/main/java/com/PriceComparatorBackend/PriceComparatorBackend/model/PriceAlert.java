package com.PriceComparatorBackend.PriceComparatorBackend.model;

import java.time.LocalDate;

public class PriceAlert {
    private String alertId;
    private String userId;
    private Double targetPrice;
    private String productName;
    private String productBrand;
    private LocalDate dateCreated;

    public PriceAlert() {
    }

    public PriceAlert(String alertId, String userId, Double targetPrice,
            String productName, String productBrand, LocalDate dateCreated) {
        this.alertId = alertId;
        this.userId = userId;
        this.targetPrice = targetPrice;
        this.productName = productName;
        this.productBrand = productBrand;
        this.dateCreated = dateCreated;
    }

    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(Double targetPrice) {
        this.targetPrice = targetPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
}
