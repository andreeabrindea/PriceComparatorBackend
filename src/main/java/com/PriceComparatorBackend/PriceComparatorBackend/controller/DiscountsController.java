package com.PriceComparatorBackend.PriceComparatorBackend.controller;
import java.util.List;

import com.PriceComparatorBackend.PriceComparatorBackend.model.Discount;
import com.PriceComparatorBackend.PriceComparatorBackend.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DiscountsController {
    private final DiscountService discountService;
    
    @Autowired
    public DiscountsController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/discounts")
    public ResponseEntity<List<Discount>> optimiseShoppingListForCostSaving() {
            return ResponseEntity.ok(discountService.getBiggestDiscounts());
    }

}
