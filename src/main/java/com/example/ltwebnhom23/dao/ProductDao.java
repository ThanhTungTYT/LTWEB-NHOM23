package com.example.ltwebnhom23.dao;
import com.example.ltwebnhom23.model.Product;

import java.util.List;

public class ProductDao extends BaseDao{

    public List<Product> getProductsBySold(){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.id, p.name, p.price, p.sold, p.stock, p.weight_grams, " +
                                // Subquery lấy ảnh đầu tiên
                                "(SELECT image_url FROM product_images i WHERE i.product_id = p.id ORDER BY i.id ASC LIMIT 1) AS image_url, " +
                                "c.name AS category_name " +
                                "FROM products p " +
                                "JOIN categories c ON p.category_id = c.id " +
                                "ORDER BY p.sold DESC " +
                                "LIMIT 4")
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public List<Product> getAllProduct(){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.id, p.category_id, p.name, p.price, p.description, p.stock, p.sold, p.weight_grams, " +
                                "(SELECT image_url FROM product_images i WHERE i.product_id = p.id ORDER BY i.id ASC LIMIT 1) AS image_url, " +
                                "c.name AS category_name, " +
                                // Tính điểm đánh giá trung bình
                                "IFNULL(AVG(r.rating), 0) AS avg_rating " +
                                "FROM products p " +
                                "JOIN categories c ON p.category_id = c.id " +
                                "LEFT JOIN products_review r ON p.id = r.product_id " +
                                "GROUP BY p.id, p.name, p.price, c.name")
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public List<Product> getProductForCategory(int cid){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.id, p.name, p.price, p.sold, " +
                                "(SELECT image_url FROM product_images i WHERE i.product_id = p.id ORDER BY i.id ASC LIMIT 1) AS image_url, " +
                                "c.name AS category_name " +
                                "FROM products p " +
                                "JOIN categories c ON p.category_id = c.id " +
                                "WHERE p.category_id = :c")
                        .bind("c", cid)
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public Product getProductById(int pid){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.*, " + // p.* sẽ lấy id, name, weight_grams... khớp Model
                                "c.name AS category_name, " +
                                "(SELECT image_url FROM product_images i WHERE i.product_id = p.id ORDER BY i.id LIMIT 1) AS image_url, " +
                                "IFNULL(AVG(r.rating), 0) AS avg_rating " +
                                "FROM products p " +
                                "JOIN categories c ON p.category_id = c.id " +
                                "LEFT JOIN products_review r ON p.id = r.product_id " +
                                "WHERE p.id = :pid " +
                                "GROUP BY p.id, c.name")
                        .bind("pid", pid)
                        .mapToBean(Product.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public List<Product> getProductsByRelative(int cid, String name, int pid){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.id, p.name, p.price, p.sold, " +
                                "(SELECT image_url FROM product_images i WHERE i.product_id = p.id ORDER BY i.id LIMIT 1) AS image_url, " +
                                "c.name AS category_name " +
                                "FROM products p " +
                                "JOIN categories c ON p.category_id = c.id " +
                                "WHERE (p.name LIKE CONCAT('%', :name, '%') OR p.category_id = :category_id) " +
                                "AND p.id != :product_id " +
                                "ORDER BY p.sold DESC LIMIT 4")
                        .bind("name", name)
                        .bind("category_id", cid)
                        .bind("product_id", pid)
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public List<Product> getFilteredProducts(int cid, String sortType) {
        return getJdbi().withHandle(handle -> {
            String sql = "SELECT p.id, p.category_id, p.name, p.price, p.sold, p.stock, p.weight_grams, " +
                    "(SELECT image_url FROM product_images i WHERE i.product_id = p.id ORDER BY i.id ASC LIMIT 1) AS image_url, " +
                    "c.name AS category_name, " +
                    "IFNULL(AVG(r.rating), 0) AS avg_rating " +
                    "FROM products p " +
                    "JOIN categories c ON p.category_id = c.id " +
                    "LEFT JOIN products_review r ON p.id = r.product_id ";

            if (cid > 0) {
                sql += "WHERE p.category_id = :cid ";
            }

            sql += "GROUP BY p.id, p.name, p.price, c.name, p.sold, p.stock, p.weight_grams, p.category_id ";

            if (sortType != null) {
                switch (sortType) {
                    case "price-desc": sql += "ORDER BY p.price DESC"; break;      // Giá cao -> thấp
                    case "price-asc":  sql += "ORDER BY p.price ASC"; break;       // Giá thấp -> cao
                    case "sold":       sql += "ORDER BY p.sold DESC"; break;       // Bán chạy nhất
                    case "rating":     sql += "ORDER BY avg_rating DESC"; break;   // Điểm đánh giá cao
                    default:           sql += "ORDER BY p.id DESC"; break;         // Mặc định (mới nhất)
                }
            } else {
                sql += "ORDER BY p.id DESC";
            }

            var query = handle.createQuery(sql);
            if (cid > 0) {
                query.bind("cid", cid);
            }
            return query.mapToBean(Product.class).list();
        });
    }
}