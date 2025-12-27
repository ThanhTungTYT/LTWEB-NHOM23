package com.example.ltwebnhom23.model;
public class Category  {
    private int id;
    private String name;

public class Category {
    private int id;
    private String name;

    public Category() {}

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Category(){}
    public int getCategory_id() {
        return id;
    }
    public void setCategory_id(int id) {
        this.id = id;
    }
    public String getCategory_name() {
        return name;
    }
    public void setCategory_name(String category_name) {
        this.name = name;
    }
}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
