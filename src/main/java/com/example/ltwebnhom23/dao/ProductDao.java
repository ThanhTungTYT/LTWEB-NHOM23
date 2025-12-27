package com.example.ltwebnhom23.dao;
import com.example.ltwebnhom23.model.Product;

import java.util.List;

public class ProductDao extends BaseDao{

    public List<Product> getProductsBySold(){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.id,\n" +
                        "(SELECT image_url FROM product_images i\n" +
                        "WHERE i.product_id = p.id \n" +
                        "ORDER BY i.id ASC LIMIT 1) AS image_url,\n" +
                        "p.name, p.price, p.sold\n" +
                        "FROM products p\n" +
                        "ORDER BY p.sold DESC\n" +
                        "LIMIT 4;")
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public List<Product> getAllProduct(){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.id,\n" +
                                "(SELECT image_url FROM product_images i \n" +
                                "WHERE i.product_id = p.id \n" +
                                "ORDER BY i.id ASC LIMIT 1) AS image_url,\n" +
                                "p.name, p.price, c.name AS category_name, p.stock, p.sold, p.weight_grams\n" +
                                "FROM products p\n" +
                                "JOIN categories c ON p.category_id = c.id\n" +
                                ";")
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public List<Product> getProductForCategory(int cid){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.id,\n" +
                                "(SELECT image_url FROM product_images i \n" +
                                "WHERE i.product_id = p.id \n" +
                                "ORDER BY i.id ASC LIMIT 1) AS image_url,\n" +
                                "p.name, p.price, c.name\n" +
                                "FROM products p\n" +
                                "JOIN categories c ON p.category_id = c.id\n" +
                                "WHERE p.category_id = :c")
                        .bind("c", cid)
                        .mapToBean(Product.class)
                        .list()
        );
    }

    public Product getProduct(int pid){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.*,\n" +
                        "(SELECT image_url FROM product_images i \n" +
                        "WHERE i.product_id = p.id \n" +
                        "ORDER BY i.id LIMIT 1) AS image_url,\n" +
                        "(SELECT GROUP_CONCAT(image_url ORDER BY id SEPARATOR ',')\n" +
                        "FROM product_images i WHERE i.product_id = p.id) \n" +
                        "AS all_images, c.name\n" +
                        "FROM products p\n" +
                        "JOIN categories c ON p.category_id = c.id\n" +
                        "WHERE p.id = :pid")
                        .bind(("pid"), pid)
                        .mapToBean(Product.class)
                        .findOne()
                        .orElse(null)
        );
    }

    public List<Product> getProductsByRelative(int cid, String name, int pid){
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.id,\n" +
                        "(SELECT image_url FROM product_images i WHERE i.product_id = p.id ORDER BY i.image_id LIMIT 1) AS image_url, p.name, p.price, c.name  FROM products p\n" +
                        "JOIN categories c ON p.category_id = c.cid\n" +
                        "WHERE (p.name LIKE CONCAT('%', :name, '%')\n" +
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

    public int insertProduct(Product p) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate("INSERT INTO products (category_id, name, description, stock, sold, weight_grams, price) " +
                                "VALUES (:category_id, :name, :description, :stock, :sold, :weight_grams, :price)")
                        .bind("category_id", p.getCategory_id())
                        .bind("name", p.getName())
                        .bind("description", p.getDescription())
                        .bind("stock", p.getStock())
                        .bind("sold", 0)
                        .bind("weight_grams", p.getWeight_grams())
                        .bind("price", p.getPrice())
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public void insertProductImage(int productId, String imageUrl) {
        getJdbi().useHandle(handle ->
                handle.createUpdate("INSERT INTO product_images (product_id, image_url) VALUES (:pid, :url)")
                        .bind("pid", productId)
                        .bind("url", imageUrl)
                        .execute()
        );
    }
}
