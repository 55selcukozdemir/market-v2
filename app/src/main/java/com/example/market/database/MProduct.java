package com.example.market.database;

public class MProduct {
    int id;
    String barcode;
    String productName;
    String catecory;
    float  price;

    public MProduct(int id, String barcode, String productName, String catecory, float price) {
        this.id = id;
        this.barcode = barcode;
        this.productName = productName;
        this.catecory = catecory;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getProductName() {
        return productName;
    }

    public String getCatecory() {
        return catecory;
    }

    public float getPrice() {
        return price;
    }
}
