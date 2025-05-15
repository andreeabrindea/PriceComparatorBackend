package com.PriceComparatorBackend.PriceComparatorBackend.model;

import java.util.List;

public class ShoppingList {
    String storeName;
    List<Product> produts;

    public ShoppingList(String storeName, List<Product> produts) {
        this.storeName = storeName;
        this.produts = produts;
    }
    
}
