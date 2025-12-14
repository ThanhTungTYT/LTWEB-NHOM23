package com.example.ltwebnhom23.model;
public class PaymentMethod  {
    private int payment_method_id;
    private String method_name;

    public  PaymentMethod( int payment_method_id, String method_name){
        this.payment_method_id = payment_method_id;
        this.method_name = method_name;
    }
    public int getPayment_method_id() {
        return payment_method_id;
    }
    public void setPayment_method_id(int payment_method_id) {
        this.payment_method_id = payment_method_id;
    }
    public String getMethod_name() {
        return method_name;
    }
    public void setMethod_name(String method_name) {
        this.method_name = method_name;
    }
}