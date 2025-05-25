package com.PriceComparatorBackend.PriceComparatorBackend.model;

import java.time.LocalDate;

public class PriceHistoryPoint {
    private String productId;
    private String productName;
    private Double price;
    private String storeName;
    private LocalDate date;

    public PriceHistoryPoint(String productId, String productName, Double price, String storeName, LocalDate date) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.storeName = storeName;
        this.date = date;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
