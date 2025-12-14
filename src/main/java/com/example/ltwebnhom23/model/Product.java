package com.example.ltwebnhom23.model;
public class Product {
    private int product_id;
    private String product_name;
    private String description;
    private int stock;
    private int sold;;
    private double price;
    private double weight;
    private Date  created_at;
    private Category category;
    private List<ProductReview> review;
    private String image_url;
    private String category_name;

    public Product(int product_id, String product_name, String description, int stock, int sold, double price, double weight, Date created_at, Category ,  List<ProductReview> review) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.description = description;
        this.stock = stock;
        this.sold = sold;
        this.price = price;
        this.weight = weight;
        this.created_at = created_at;
        this.category = category;
        this.review = new ArrayList<>();
    }
    public int getProduct_id() {
        return product_id;
    }
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductReview> getReview() {
        return review;
    }

    public void setReview(List<ProductReview> review) {
        this.review = review;
    }
    public  String getImage_url() {
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
