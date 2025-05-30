package com.PriceComparatorBackend.PriceComparatorBackend.repository;

import com.PriceComparatorBackend.PriceComparatorBackend.model.Discount;
import com.PriceComparatorBackend.PriceComparatorBackend.model.Product;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.PriceComparatorBackend.PriceComparatorBackend.Utils.Utils;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class DiscountRepository {
    private ProductRepository productRepository = new ProductRepository();

    public List<Discount> getAllDiscounts() {
        try {
            Utils.createDataset("dataset", "data");
            Utils.createDataset("discountsDataset", "data/discounts");

        } catch (Exception exception) {
            return null;
        }

        List<Discount> discounts = new ArrayList<>();
        try (CSVReader reader = new CSVReaderBuilder(
                new FileReader("src/main/java/com/PriceComparatorBackend/PriceComparatorBackend/discountsDataset.csv"))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String[] headers = reader.readNext();
            Map<String, Integer> headerMap = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                headerMap.put(headers[i].trim(), i);
            }

            String[] line;
            while ((line = reader.readNext()) != null) {
                String productId = line[headerMap.get("product_id")];
                Product product = productRepository.findProductById(productId);
                if (product != null) {
                    product.setDate(LocalDate.parse(line[headerMap.get("date")], formatter));
                    discounts.add(new Discount(
                            product,
                            LocalDate.parse(line[headerMap.get("from_date")], formatter),
                            LocalDate.parse(line[headerMap.get("to_date")], formatter),
                            Integer.parseInt(line[headerMap.get("percentage_of_discount")])));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return discounts;
    }

    List<Discount> getDiscountForCurrentProduct(Product product) {
        List<Discount> discounts = getAllDiscounts();

        return discounts.stream()
                .filter(d -> d.getProduct().getProductId().equalsIgnoreCase(product.getProductId()))
                .collect(Collectors.toList());
    }
}
