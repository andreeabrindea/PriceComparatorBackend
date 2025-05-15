package com.PriceComparatorBackend.PriceComparatorBackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Product {
    @Id
    private String productId;

    @NotBlank(message = "Name is required")
    String productName;
    String productCategory;
    String brand;
    int packageQuantity;
    int unit;
    double price;
    String currency;

    public Product(String productId, String productName, String productCategory, String brand, int packageQuantity,
            int unit, double price, String currency) {
        this.productId = productId;
        this.productName = productName;
        this.productCategory = productCategory;
        this.brand = brand;
        this.packageQuantity = packageQuantity;
        this.unit = unit;
        this.price = price;
        this.currency = currency;
    }
}
