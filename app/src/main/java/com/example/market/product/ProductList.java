package com.example.market.product;

public class ProductList {
    String productName;
    String barcode;
    Float price;
    String category;
    int number;
    int Pictures;


    public ProductList(String productName, String barcode, Float price, int number, int pictures, String category) {
        this.productName = productName;
        this.barcode = barcode;
        this.price = price;
        this.number = number;
        this.category = category;
        Pictures = pictures;
    }

    public String getProductName() {
        return productName;
    }

    public ProductList setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getBarcode() {
        return barcode;
    }

    public ProductList setBarcode(String barcode) {
        this.barcode = barcode;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public ProductList setPrice(Float price) {
        this.price = price;
        return this;
    }

    public int getNumber() {
        return number;
    }

    public ProductList setNumber(int number) {
        this.number = number;
        return this;
    }

    public int getPictures() {
        return Pictures;
    }

    public ProductList setPictures(int pictures) {
        Pictures = pictures;
        return this;
    }
    public String getCategory() {
        return category;
    }

}
