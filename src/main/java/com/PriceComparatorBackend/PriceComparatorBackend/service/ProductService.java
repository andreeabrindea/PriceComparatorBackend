package com.PriceComparatorBackend.PriceComparatorBackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.PriceComparatorBackend.PriceComparatorBackend.Utils.ProductRequest;
import com.PriceComparatorBackend.PriceComparatorBackend.model.Product;
import com.PriceComparatorBackend.PriceComparatorBackend.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Map<String, List<Product>> getCheapestProducts(List<ProductRequest> basketProducts) {
        List<Product> products = findProductsWithGivenNames(basketProducts, productRepository.getAllProducts());
        Map<String, List<Product>> productsGroupedByName = products.stream()
                .collect(Collectors.groupingBy(p -> p.getProductName()));

        List<Product> cheapestProducts = new ArrayList<>();
        for (Map.Entry<String, List<Product>> entry : productsGroupedByName.entrySet()) {
            List<Product> groupedProducts = entry.getValue();
            groupedProducts.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
            cheapestProducts.add(groupedProducts.get(0));
        }

        return cheapestProducts.stream().collect(Collectors.groupingBy(Product::getStoreName));
    }

    public List<Product> substitute(String name) {
        List<Product> products = productRepository.getAllProducts().stream()
                .filter(p -> p.getProductName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        products.sort(
                (a, b) -> Double.compare(a.getPrice() / a.getPackageQuantity(), b.getPrice() / b.getPackageQuantity()));
        return products;
    }

    private List<Product> findProductsWithGivenNames(List<ProductRequest> basketProducts,
            List<Product> availableProducts) {
        List<Product> wantedProducts = new ArrayList<>();
        for (ProductRequest request : basketProducts) {
            for (Product product : availableProducts) {
                if (product.getProductName().contains(request.getProductName())) {
                    wantedProducts.add(product);
                }
            }
        }

        return wantedProducts;
    }
}
