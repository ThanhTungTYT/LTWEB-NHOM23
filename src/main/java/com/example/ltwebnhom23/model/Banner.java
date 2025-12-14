package com.example.ltwebnhom23.model;
public class Banner {
    private int banner_id;
    private List<String> banner_url;
    private String status;
    private Date startDate;
    private  Date endDate;

    public Banner(int banner_id, List<String> banner_url, String status Date startDate, Date endDate) {
        this.banner_id = banner_id;
        this.banner_url = banner_url;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public int getBanner_id() {
        return banner_id;
    }
    public void setBanner_id(int banner_id) {
        this.banner_id = banner_id;
    }
    public List<String> getBanner_url() {
        return banner_url;
    }
    public void setBanner_url(List<String> banner_url) {
        this.banner_url = banner_url;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}