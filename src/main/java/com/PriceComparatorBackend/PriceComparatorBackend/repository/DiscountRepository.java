package com.PriceComparatorBackend.PriceComparatorBackend.repository;

import com.PriceComparatorBackend.PriceComparatorBackend.model.Discount;
import com.PriceComparatorBackend.PriceComparatorBackend.model.Product;
import com.PriceComparatorBackend.PriceComparatorBackend.Utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class DiscountRepository {
    private ProductRepository productRepository = new ProductRepository();

    public List<Discount> getAllDiscounts() {
        try {
            Utils.createDataset("dataset", "data");
            Utils.createDataset("discountsDataset", "data/discounts");

        } catch (Exception exception) {
            System.out.println("failed to create dataset");
            return null;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(
                "src/main/java/com/PriceComparatorBackend/PriceComparatorBackend/discountsDataset.csv"))) {
            String line;
            int lineCount = 0;
            List<Discount> discounts = new ArrayList<Discount>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while ((line = br.readLine()) != null) {
                if (lineCount > 0) {
                    String[] entries = line.split(";");
                    String productId = entries[0];
                    LocalDate fromDate = LocalDate.parse(entries[6], formatter);
                    LocalDate toDate = LocalDate.parse(entries[7], formatter);
                    int discountPercent = Integer.parseInt(entries[8]);
                    Product product = productRepository.findProductById(productId);

                    if (product != null) {
                        discounts.add(new Discount(product, fromDate, toDate, discountPercent));
                    } else {
                        System.out.println("Product not found for ID: " + productId);
                    }
                }
                lineCount++;
            }

            return discounts;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
