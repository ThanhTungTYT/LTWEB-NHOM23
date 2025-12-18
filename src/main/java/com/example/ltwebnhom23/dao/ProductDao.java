package com.example.ltwebnhom23.dao;
import com.example.ltwebnhom23.model.Product;

import java.util.List;

public class ProductDao extends BaseDao{

    public List<Product> getProductsBySold(){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.product_id,\n" +
                        "(SELECT image_url FROM product_images i\n" +
                        "WHERE i.product_id = p.product_id \n" +
                        "ORDER BY i.image_id ASC LIMIT 1) AS image_url,\n" +
                        "p.product_name, p.price, p.sold\n" +
                        "FROM products p\n" +
                        "ORDER BY p.sold DESC\n" +
                        "LIMIT 4;")
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public List<Product> getAllProduct(){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.product_id,\n" +
                                "(SELECT image_url FROM product_images i \n" +
                                "WHERE i.product_id = p.product_id \n" +
                                "ORDER BY i.image_id ASC LIMIT 1) AS image_url,\n" +
                                "p.product_name, p.price, c.category_name\n" +
                                "FROM products p\n" +
                                "JOIN categories c ON p.category_id = c.category_id\n" +
                                ";")
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public List<Product> getProductForCategory(int cid){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.product_id,\n" +
                                "(SELECT image_url FROM product_images i \n" +
                                "WHERE i.product_id = p.product_id \n" +
                                "ORDER BY i.image_id ASC LIMIT 1) AS image_url,\n" +
                                "p.product_name, p.price, c.category_name\n" +
                                "FROM products p\n" +
                                "JOIN categories c ON p.category_id = c.category_id\n" +
                                "WHERE p.category_id = :c")
                        .bind("c", cid)
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public Product getProdutct(int pid){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.*,\n" +
                        "(SELECT image_url FROM product_images i \n" +
                        "WHERE i.product_id = p.product_id \n" +
                        "ORDER BY i.image_id LIMIT 1) AS image_url,\n" +
                        "(SELECT GROUP_CONCAT(image_url ORDER BY image_id SEPARATOR ',')\n" +
                        "FROM product_images i WHERE i.product_id = p.product_id) \n" +
                        "AS all_images, c.category_name\n" +
                        "FROM products p\n" +
                        "JOIN categories c ON p.category_id = c.category_id\n" +
                        "WHERE p.product_id = :pid")
                        .bind(("pid"), pid)
                        .mapToBean(Product.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public List<Product> getProductsByRelative(int cid, String name, int pid){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.product_id,\n" +
                        "(SELECT image_url FROM product_images i WHERE i.product_id = p.product_id ORDER BY i.image_id LIMIT 1) AS image_url, p.product_name, p.price, c.category_name  FROM products p\n" +
                        "JOIN categories c ON p.category_id = c.category_id\n" +
                        "WHERE (p.product_name LIKE CONCAT('%', :name, '%')\n" +
                        "OR p.category_id = :category_id)\n" +
                        "AND p.product_id != :product_id\n" +
                        "ORDER BY p.sold DESC LIMIT 4;")
                        .bind("name", name)
                        .bind("category_id", cid)
                        .bind("product_id", pid)
                        .mapToBean(Product.class)
                        .list()
        );
    }
}
