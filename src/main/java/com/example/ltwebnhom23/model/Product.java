package com.example.ltwebnhom23.model;

public class Product {
    private int product_id;
    private int category_id;
    private String product_name;
    private String description;
    private int stock;
    private int sold;
    private String weight_gram;
    private double price;
    private String image_url;
    private String category_name;

    public Product(int product_id, int category_id, String product_name, String description, int stock, int sold, String weight_gram, double price) {
        this.product_id = product_id;
        this.category_id = category_id;
        this.product_name = product_name;
        this.description = description;
        this.stock = stock;
        this.sold = sold;
        this.weight_gram = weight_gram;
        this.price = price;
    }

    public Product(){}

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public String getWeiht_gram() {
        return weight_gram;
    }

    public void setWeiht_gram(String weight_gram) {
        this.weight_gram = weight_gram;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
