package com.PriceComparatorBackend.PriceComparatorBackend.model;

public class PriceAlertRequest {
    private String alertId;
    private String userId;
    private Double targetPrice;
    private String productName;
    private String brand;

    public PriceAlertRequest() {
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String productBrand) {
        this.brand = productBrand;
    }
}
