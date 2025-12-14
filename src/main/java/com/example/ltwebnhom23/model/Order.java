package com.example.ltwebnhom23.model;

public class Order {
    private int order_id;
    private User user;
    private PaymentMethod method;
    private Promotion promo;
    private String receiver_name;
    private String receiver_phone;
    private double total_amount;
    private double shipping_fee;
    private double discount_percent;
    private double final_amount;
    private String note;
    private String status;
    private Date created_at;

    public Order(int order_id, User user, PaymentMethod method, Promotion promo, String receiver_name, String receiver_phone, double total_amount, double shipping_fee,double discount_percent, double final_amount,String note, String status, Date created_at) {
        this.order_id = order_id;
        this.user = user;
        this.method = method;
        this.promo = promo;
        this.receiver_name = receiver_name;
        this.receiver_phone = receiver_phone;
        this.total_amount = total_amount;
        this.shipping_fee = shipping_fee;
        this.discount_percent = discount_percent;
        this.final_amount = final_amount;
        this.note = note;
        this.status = status;
        this.created_at = created_at;
    }
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public Promotion getPromo() {
        return promo;
    }

    public void setPromo(Promotion promo) {
        this.promo = promo;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public double getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(double shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public double getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(double discount_percent) {
        this.discount_percent = discount_percent;
    }

    public double getFinal_amount() {
        return final_amount;
    }

    public void setFinal_amount(double final_amount) {
        this.final_amount = final_amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

}
