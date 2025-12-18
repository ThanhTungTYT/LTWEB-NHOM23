package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.dao.ProductDao;
import com.example.ltwebnhom23.model.Product;

import java.util.List;

public class ProductService {
    private ProductDao p = new ProductDao();

    public List<Product> getProductsBySold(){
        return p.getProductsBySold();
    }
    public List<Product> getAllProduct(){
        return p.getAllProduct();
    }
    public List<Product> getProductForCategory(int cid){
        return p.getProductForCategory(cid);
    }
}
