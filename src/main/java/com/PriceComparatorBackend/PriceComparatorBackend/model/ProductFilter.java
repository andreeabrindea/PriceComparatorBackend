package com.PriceComparatorBackend.PriceComparatorBackend.model;

import java.time.LocalDate;

public class ProductFilter {
    private String productId;
    private String productName;
    private String storeName;
    private String productCategory;
    private String brand;
    private LocalDate startDate;
    private LocalDate endDate;

    public ProductFilter() {
    }

    public ProductFilter(String productId, String productName, String storeName, String productCategory,
            String brand, LocalDate startDate, LocalDate endDate) {
        this.productId = productId;
        this.productName = productName;
        this.storeName = storeName;
        this.productCategory = productCategory;
        this.brand = brand;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
