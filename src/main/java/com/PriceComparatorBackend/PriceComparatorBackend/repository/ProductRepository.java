package com.PriceComparatorBackend.PriceComparatorBackend.repository;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.PriceComparatorBackend.PriceComparatorBackend.model.Product;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.PriceComparatorBackend.PriceComparatorBackend.Utils.Utils;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    public List<Product> getAllProducts() {
        try {
            Utils.createDataset("dataset", "data");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }

        List<Product> products = new ArrayList<>();
        try (CSVReader reader = new CSVReaderBuilder(
                new FileReader("src/main/java/com/PriceComparatorBackend/PriceComparatorBackend/dataset.csv"))
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
                products.add(new Product(
                        line[headerMap.get("product_id")],
                        line[headerMap.get("product_name")],
                        line[headerMap.get("product_category")],
                        line[headerMap.get("brand")],
                        Double.parseDouble(line[headerMap.get("package_quantity")]),
                        line[headerMap.get("package_unit")],
                        Double.parseDouble(line[headerMap.get("price")]),
                        line[headerMap.get("currency")],
                        line[headerMap.get("store_name")],
                        LocalDate.parse(line[headerMap.get("date")], formatter)));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    public Product findProductById(String id) {
        List<Product> products = getAllProducts();
        return products.stream().filter(p -> p.getProductId().equalsIgnoreCase(id)).findAny().orElse(null);
    }
}
