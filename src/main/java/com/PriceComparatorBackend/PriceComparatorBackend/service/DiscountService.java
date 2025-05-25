package com.PriceComparatorBackend.PriceComparatorBackend.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.PriceComparatorBackend.PriceComparatorBackend.repository.DiscountRepository;
import com.PriceComparatorBackend.PriceComparatorBackend.model.Discount;

import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public List<Discount> getBiggestDiscounts() {
        List<Discount> discounts = discountRepository.getAllDiscounts();
        discounts.sort((a, b) -> Double.compare(b.getPercentageOfDiscount(), a.getPercentageOfDiscount()));
        return discounts.stream().limit(5).collect(Collectors.toList());
    }

    public List<Discount> getLatestDiscounts() {
        List<Discount> discounts = discountRepository.getAllDiscounts();
        return discounts.stream().filter(
                d -> ChronoUnit.HOURS.between(d.getProduct().getDate().atStartOfDay(), LocalDateTime.now()) <= 24)
                .collect(Collectors.toList());
    }

}
