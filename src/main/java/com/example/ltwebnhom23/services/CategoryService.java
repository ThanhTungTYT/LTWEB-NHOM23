package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.dao.CategoryDao;
import com.example.ltwebnhom23.model.Category;

import java.util.List;

public class CategoryService {

    private CategoryDao categoryDao = new CategoryDao();

    public List<Category> getAllCategories(){
        return categoryDao.getAllCategories();
    }
}