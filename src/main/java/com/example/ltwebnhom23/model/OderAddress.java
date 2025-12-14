package com.example.ltwebnhom23.model;
public class OderAddress{
    private int order_address_id;
    private String country;
    private String province;
    private String ward;
    private String address;

    public OderAddress(int order_address_id, String country, String province, String ward, String address) {
        this.order_address_id = order_address_id;
        this.country = country;
        this.province = province;
        this.ward = ward;
        this.address = address;
    }
    public int getOrder_address_id() {
        return order_address_id;
    }
    public void setOrder_address_id(int order_address_id) {
        this.order_address_id = order_address_id;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getWard() {
        return ward;
    }
    public void setWard(String ward) {
        this.ward = ward;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}