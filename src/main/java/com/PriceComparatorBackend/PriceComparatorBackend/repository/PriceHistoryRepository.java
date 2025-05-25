package com.PriceComparatorBackend.PriceComparatorBackend.repository;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.PriceComparatorBackend.PriceComparatorBackend.model.Discount;
import com.PriceComparatorBackend.PriceComparatorBackend.model.PriceHistoryPoint;
import com.PriceComparatorBackend.PriceComparatorBackend.model.Product;

@Repository
public class PriceHistoryRepository {
    private ProductRepository productRepository = new ProductRepository();
    private DiscountRepository discountRepository = new DiscountRepository();

    public PriceHistoryRepository(ProductRepository productRepository, DiscountRepository discountRepository) {
        this.productRepository = productRepository;
        this.discountRepository = discountRepository;
    }

    public List<PriceHistoryPoint> applyDiscountsAndGetPriceHistoryForProduct(Product product) {

        List<Product> productRecords = productRepository.getAllProducts().stream()
                .filter(p -> p.getProductId().equalsIgnoreCase(product.getProductId())).collect(Collectors.toList());

        List<PriceHistoryPoint> priceHistoryPoints = new ArrayList<>();
        if (discountRepository.getDiscountForCurrentProduct(product).size() == 0 && productRecords.size() == 0) {
            PriceHistoryPoint priceHistoryPoint = new PriceHistoryPoint(product.getProductId(),
                    product.getProductName(), product.getPrice(), product.getStoreName(), product.getDate());

            priceHistoryPoints.add(priceHistoryPoint);

            return priceHistoryPoints;
        }

        for (Discount discout : discountRepository.getDiscountForCurrentProduct(product)) {
            if (product.getDate().isAfter(discout.getFromDate()) && product.getDate().isBefore(discout.getToDate())) {
                Double priceWithDiscount = product.getPrice()
                        - (discout.getPercentageOfDiscount() / 100.0 * product.getPrice());

                DecimalFormat df = new DecimalFormat("#.00");
                PriceHistoryPoint priceHistoryPoint = new PriceHistoryPoint(product.getProductId(),
                        product.getProductName(),
                        Double.valueOf(df.format(priceWithDiscount)),
                        product.getStoreName(),
                        product.getDate());

                priceHistoryPoints.add(priceHistoryPoint);
            }
        }
        for (Product productRecord : productRecords) {
            PriceHistoryPoint priceHistoryPoint = new PriceHistoryPoint(productRecord.getProductId(),
                    productRecord.getProductName(),
                    product.getPrice(),
                    product.getStoreName(),
                    product.getDate());
            priceHistoryPoints.add(priceHistoryPoint);
        }

        return priceHistoryPoints;

    }
}
