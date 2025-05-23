package com.PriceComparatorBackend.PriceComparatorBackend.Utils;

public class ProductRequest {
    String productName;

    public ProductRequest(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
