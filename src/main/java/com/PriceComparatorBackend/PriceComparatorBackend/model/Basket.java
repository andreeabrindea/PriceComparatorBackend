package com.PriceComparatorBackend.PriceComparatorBackend.model;

import java.util.List;

public class Basket {
    List<ShoppingList> shoppingLists;

    public Basket(List<ShoppingList> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    public List<ShoppingList> getShoppingLists() {
        return shoppingLists;
    }
}
