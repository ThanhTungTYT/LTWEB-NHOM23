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
        return productDao.getProductById(pid);
    }

    public List<Product> getProductsByRelative(int cid, String name, int pid){
        return productDao.getProductsByRelative(cid, name, pid);
    }

    // --- CẬP NHẬT PHÂN TRANG ---
    public List<Product> getProductsForCatalog(int cid, String sort, int page) {
        if (sort == null) sort = "default";
        int offset = (page - 1) * 25; // Tính vị trí bắt đầu
        return productDao.getFilteredProducts(cid, sort, offset);
    }

    public int getTotalPages(int cid) {
        int totalProducts = productDao.countProducts(cid);
        return (int) Math.ceil((double) totalProducts / 25);
    }
    // ---------------------------

    public boolean addProductWithUrls(Product product, String[] imageUrls) {
        try {
            int newProductId = productDao.insertProduct(product); // Sửa p -> productDao
            if (newProductId > 0) {
                if (imageUrls != null && imageUrls.length > 0) {
                    for (String url : imageUrls) {
                        if (url != null && !url.trim().isEmpty()) {
                            productDao.insertProductImage(newProductId, url.trim()); // Sửa p -> productDao
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