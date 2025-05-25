package com.PriceComparatorBackend.PriceComparatorBackend.model;


public class ProductFilter {
    private String storeName;
    private String productCategory;
    private String brand;


    public ProductFilter() {
    }

    public ProductFilter(String storeName, String productCategory, String brand) {

        this.storeName = storeName;
        this.productCategory = productCategory;
        this.brand = brand;
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
}
