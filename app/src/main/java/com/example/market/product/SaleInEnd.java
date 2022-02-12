package com.example.market.product;

public class SaleInEnd {
    String saleInEndProduct;

    public SaleInEnd(String saleInEndProduct, String saleInEndPrices, String saleInEndTotalPrice, String saleInEndCourses) {
        this.saleInEndProduct = saleInEndProduct;
        this.saleInEndPrices = saleInEndPrices;
        this.saleInEndTotalPrice = saleInEndTotalPrice;
        this.saleInEndCourses = saleInEndCourses;
    }

    String saleInEndPrices;
    String saleInEndTotalPrice;
    String saleInEndCourses;

    public String getSaleInEndProduct() {
        return saleInEndProduct;
    }

    public SaleInEnd setSaleInEndProduct(String saleInEndProduct) {
        this.saleInEndProduct = saleInEndProduct;
        return this;
    }

    public String getSaleInEndPrices() {
        return saleInEndPrices;
    }

    public SaleInEnd setSaleInEndPrices(String saleInEndPrices) {
        this.saleInEndPrices = saleInEndPrices;
        return this;
    }

    public String getSaleInEndTotalPrice() {
        return saleInEndTotalPrice;
    }

    public SaleInEnd setSaleInEndTotalPrice(String saleInEndTotalPrice) {
        this.saleInEndTotalPrice = saleInEndTotalPrice;
        return this;
    }

    public String getSaleInEndNumber() {
        return saleInEndCourses;
    }

    public SaleInEnd setSaleInEndCourses(String saleInEndCourses) {
        this.saleInEndCourses = saleInEndCourses;
        return this;
    }
}
