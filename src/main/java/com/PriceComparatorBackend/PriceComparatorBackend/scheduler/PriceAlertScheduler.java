package com.PriceComparatorBackend.PriceComparatorBackend.scheduler;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.PriceComparatorBackend.PriceComparatorBackend.model.PriceAlert;
import com.PriceComparatorBackend.PriceComparatorBackend.repository.PriceAlertRepository;

@Component
public class PriceAlertScheduler {
     private final PriceAlertRepository priceAlertRepository;
    
    @Autowired
    public PriceAlertScheduler(PriceAlertRepository priceAlertRepository) {
        this.priceAlertRepository = priceAlertRepository;
    }
    @Scheduled(fixedRate = 360)
    public void scheduleAlert() {
        try {
            List<PriceAlert> alerts = priceAlertRepository.getAllAlerts();
            
            if (!alerts.isEmpty()) {
                System.out.println("Price alerts triggered: " + alerts.size());
                for (PriceAlert alert : alerts) {
                    System.out.println("Alert created for product: " + alert.getProductName() + 
                                     " Target: " + alert.getTargetPrice() + 
                                     ", Current: " + priceAlertRepository.getCurrentPriceForProduct(alert.getProductName(), alert.getProductBrand()));
                }
            }
        } catch (Exception e) {
            System.err.println("Error checking price alerts: " + e.getMessage());
        }
    }
}
