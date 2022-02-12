package com.example.market.database;

public class MSale {
    int id;
    String barcode;
    String productName;
    String catecory;
    float  price;
    int number;

    public MSale(int id, String barcode, String productName, String catecory, float price, int number) {
        this.id = id;
        this.barcode = barcode;
        this.productName = productName;
        this.catecory = catecory;
        this.price = price;
        this.number = number;
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

    public int getNumber() {
        return number;
    }
}
