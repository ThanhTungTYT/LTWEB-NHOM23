package com.example.ltwebnhom23.dao;

import com.example.ltwebnhom23.model.Category;

import java.util.List;

public class CategoryDao extends BaseDao {

    public List<Category> getAllCategories(){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT * FROM categories")
                        .mapToBean(Category.class)
                        .list()
        );
    }

    public boolean insertCategory(String name) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate("INSERT INTO categories (name) VALUES (:name)")
                        .bind("name", name)
                        .execute() > 0
        );
    }
}