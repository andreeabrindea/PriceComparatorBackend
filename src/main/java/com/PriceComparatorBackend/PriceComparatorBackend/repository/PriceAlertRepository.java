package com.PriceComparatorBackend.PriceComparatorBackend.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import java.io.*;

import com.PriceComparatorBackend.PriceComparatorBackend.model.PriceAlert;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

@Repository
public class PriceAlertRepository {
    private static final String ALERTS_FILE = "src/main/java/com/PriceComparatorBackend/PriceComparatorBackend/price_alerts.csv";
    private ProductRepository productRepository = new ProductRepository();

    public List<PriceAlert> getAllAlerts() {
        List<PriceAlert> alerts = new ArrayList<>();
        File file = new File(ALERTS_FILE);

        if (!file.exists()) {
            return alerts;
        }

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(ALERTS_FILE))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            String[] headers = reader.readNext();
            if (headers == null)
                return alerts;

            Map<String, Integer> headerMap = new HashMap<>();
            for (int i = 0; i < headers.length; i++) {
                headerMap.put(headers[i].trim(), i);
            }

            String[] line;
            while ((line = reader.readNext()) != null) {
                PriceAlert alert = new PriceAlert();
                alert.setAlertId(line[headerMap.get("alert_id")]);
                alert.setUserId(line[headerMap.get("user_id")]);
                alert.setTargetPrice(Double.parseDouble(line[headerMap.get("target_price")]));
                alert.setProductName(line[headerMap.get("product_name")]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                alert.setDateCreated(LocalDate.parse(line[headerMap.get("created_at")], formatter));
                alerts.add(alert);
            }
        } catch (Exception e) {
            System.out.println("Error reading alerts: " + e.getMessage());
        }

        return alerts;
    }

    public PriceAlert saveAlert(PriceAlert alert) {
        if (alert.getAlertId() == null) {
            alert.setAlertId(UUID.randomUUID().toString());
        }

        List<PriceAlert> alerts = getAllAlerts();
        alerts.removeIf(a -> a.getAlertId().equals(alert.getAlertId()));
        alerts.add(alert);

        saveAllAlerts(alerts);
        return alert;
    }

    private void saveAllAlerts(List<PriceAlert> alerts) {
        try {
            File file = new File(ALERTS_FILE);
            file.getParentFile().mkdirs();

            try (CSVWriter writer = new CSVWriter(new FileWriter(ALERTS_FILE), ';',
                    CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END)) {

                String[] headers = { "alert_id", "product_id", "user_id", "target_price", "product_name", "brand",
                        "created_at" };
                writer.writeNext(headers);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                for (PriceAlert alert : alerts) {
                    String[] data = {
                            alert.getAlertId(),
                            alert.getUserId(),
                            alert.getTargetPrice().toString(),
                            alert.getProductName() != null ? alert.getProductName() : "",
                            alert.getDateCreated().format(formatter)
                    };
                    writer.writeNext(data);
                }
            }
        } catch (Exception e) {
            System.out.println("Error saving alerts: " + e.getMessage());
        }
    }

    public boolean deleteAlert(String alertId) {
        List<PriceAlert> alerts = getAllAlerts();
        boolean removed = alerts.removeIf(alert -> alert.getAlertId().equals(alertId));

        if (removed) {
            saveAllAlerts(alerts);
        }

        return removed;
    }

    public Double getCurrentPriceForProduct(String name, String brand) {
        return productRepository.getAllProducts().stream()
                .filter(p -> p.getProductName().equalsIgnoreCase(name))
                .filter(p -> p.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList()).get(0).getPrice();
    }
}
