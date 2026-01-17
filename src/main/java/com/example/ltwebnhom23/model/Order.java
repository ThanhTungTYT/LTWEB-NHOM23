package com.example.ltwebnhom23.model;

import java.sql.Timestamp;

public class Order {
    private int id;
    private int userId;
    private Integer promoId;
    private double total;
    private double discount;
    private double finalAmount;
    private Timestamp createdAt;

    public void setUserId(int userId) { this.userId = userId; }
    public void setPromoId(Integer promoId) { this.promoId = promoId; }
    public void setTotal(double total) { this.total = total; }
    public void setDiscount(double discount) { this.discount = discount; }
    public void setFinalAmount(double finalAmount) { this.finalAmount = finalAmount; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
