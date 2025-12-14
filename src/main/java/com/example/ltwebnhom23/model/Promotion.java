package com.example.ltwebnhom23.model;
public class Promotion {
    private int promo_id;
    private String promo_code;
    private String description;
    private double discount_percent;
    private double min_order_value;
    private Date start_date;
    private Date end_date;

    public Promotion(int promo_id, String promo_code, String description, double discount_percent, double min_order_value Date start_date, Date end_date) {
        this.promo_id = promo_id;
        this.promo_code = promo_code;
        this.description = description;
        this.discount_percent = discount_percent;
        this.min_order_value = min_order_value;
        this.start_date = start_date;
        this.end_date = end_date;
    }
    public int getPromo_id() {
        return promo_id;
    }
    public void setPromo_id(int promo_id) {
        this.promo_id = promo_id;
    }
    public String getPromo_code() {
        return promo_code;
    }
    public void setPromo_code(String promo_code) {
        this.promo_code = promo_code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getDiscount_percent() {
        return discount_percent;
    }
    public void setDiscount_percent(double discount_percent) {
        this.discount_percent = discount_percent;
    }
    public Date getStart_date() {
        return start_date;
    }
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }
    public Date getEnd_date() {
        return end_date;
    }
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
    public double getMin_oder_value() {
        return min_oder_value;
    }
    public void setMin_oder_value(double min_oder_value) {
        this.min_oder_value = min_oder_value;
    }

}