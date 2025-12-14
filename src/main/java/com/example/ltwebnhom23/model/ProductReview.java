package com.example.ltwebnhom23.model;
public class ProductReview {
    private int review_id;
    private int rating;
    private String comment;
    private Date created_at;

    public ProductReview(int review_id, int rating, String comment, Date created_at) {
        this.review_id = review_id;
        this.rating = rating;
        this.comment = comment;
        this.created_at = created_at;
    }
    public int getReview_id() {
        return review_id;
    }
    public void setReview_id(int review_id) {
        this.review_id = review_id;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}