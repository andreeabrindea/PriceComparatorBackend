package com.PriceComparatorBackend.PriceComparatorBackend.controller;

import java.util.List;
import java.util.Map;

import com.PriceComparatorBackend.PriceComparatorBackend.Utils.ProductRequest;
import com.PriceComparatorBackend.PriceComparatorBackend.model.Product;
import com.PriceComparatorBackend.PriceComparatorBackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<Map<String, List<Product>>> optimiseShoppingListForCostSaving(
            @RequestBody List<ProductRequest> products) {
        try {
            return ResponseEntity.ok(productService.getCheapestProducts(products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> substituteProduct(@RequestParam(required = true) String name){
        try {
            return ResponseEntity.ok(productService.substitute(name));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
