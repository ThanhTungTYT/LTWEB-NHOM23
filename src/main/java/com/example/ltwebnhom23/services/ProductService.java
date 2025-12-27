package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.dao.ProductDao;
import com.example.ltwebnhom23.model.Product;

import java.util.List;

public class ProductService {
    private ProductDao productDao = new ProductDao();

    public List<Product> getProductsBySold(){
        return productDao.getProductsBySold();
    }

    public List<Product> getAllProduct(){
        return productDao.getAllProduct();
    }

    public List<Product> getProductForCategory(int cid){
        return productDao.getProductForCategory(cid);
    }

    public Product getProduct(int pid){
        return p.getProduct(pid);
        return productDao.getProductById(pid);
    }

    public List<Product> getProductsByRelative(int cid, String name, int pid){
        return productDao.getProductsByRelative(cid, name, pid);
    }

    public List<Product> getProductsForCatalog(int cid, String sort) {
        if (sort == null) sort = "default";
        return productDao.getFilteredProducts(cid, sort);
    }
    public boolean addProductWithUrls(Product product, String[] imageUrls) {
        try {
            int newProductId = p.insertProduct(product);
            if (newProductId > 0) {
                if (imageUrls != null && imageUrls.length > 0) {
                    for (String url : imageUrls) {
                        if (url != null && !url.trim().isEmpty()) {
                            p.insertProductImage(newProductId, url.trim());
                        }
                    }
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
}
