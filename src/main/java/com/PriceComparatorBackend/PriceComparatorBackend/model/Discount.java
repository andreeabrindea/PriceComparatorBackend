package com.PriceComparatorBackend.PriceComparatorBackend.model;

import java.time.LocalDate;

public class Discount {
    Product product;
    LocalDate fromDate;
    LocalDate toDate;
    int percentageOfDiscount;

    public Discount(Product product, LocalDate fromDate, LocalDate toDate, int percentageOfDiscount) {
        this.product = product;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.percentageOfDiscount = percentageOfDiscount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public int getPercentageOfDiscount() {
        return percentageOfDiscount;
    }

    public void setPercentageOfDiscount(int percentageOfDiscount) {
        this.percentageOfDiscount = percentageOfDiscount;
    }

}

