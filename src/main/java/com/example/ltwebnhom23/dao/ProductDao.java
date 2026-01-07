package com.example.ltwebnhom23.dao;
import com.example.ltwebnhom23.model.Product;
import java.util.List;

public class ProductDao extends BaseDao {

    // Lấy 4 sản phẩm bán chạy nhất
    public List<Product> getProductsBySold() {
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.id, p.name, p.price, p.sold, p.stock, p.weight_grams, " +
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

    // Lấy toàn bộ sản phẩm (cho trang Admin hoặc Debug)
    public List<Product> getAllProduct() {
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.id, p.category_id, p.name, p.price, p.description, p.stock, p.sold, p.weight_grams, " +
                                "(SELECT image_url FROM product_images i WHERE i.product_id = p.id ORDER BY i.id ASC LIMIT 1) AS image_url, " +
                                "c.name AS category_name, " +
                                "IFNULL(AVG(r.rating), 0) AS avg_rating " +
                                "FROM products p " +
                                "JOIN categories c ON p.category_id = c.id " +
                                "LEFT JOIN products_review r ON p.id = r.product_id " +
                                "Where p.state ='active' And c.state = 'active' "+
                                "GROUP BY p.id, p.name, p.price, c.name")
                        .mapToBean(Product.class)
                        .list()
        );
    }

    // Lấy sản phẩm theo danh mục (cơ bản)
    public List<Product> getProductForCategory(int cid) {
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

    // Lấy chi tiết 1 sản phẩm
    public Product getProductById(int pid) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT p.*, " +
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

    // Lấy sản phẩm liên quan
    public List<Product> getProductsByRelative(int cid, String name, int pid) {
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

    // --- PHẦN PHÂN TRANG & LỌC (Catalog) ---

    // 1. Đếm tổng số sản phẩm để tính số trang
    public int countProducts(int cid) {
        return getJdbi().withHandle(handle -> {
            String sql = "SELECT COUNT(*) FROM products p ";
            if (cid > 0) {
                sql += "WHERE p.category_id = :cid";
            }
            var query = handle.createQuery(sql);
            if (cid > 0) query.bind("cid", cid);
            return query.mapTo(Integer.class).one();
        });
    }

    // 2. Lấy danh sách sản phẩm có phân trang (LIMIT, OFFSET)
    public List<Product> getFilteredProducts(int cid, String sortType, int offset) {
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
                    case "price-desc": sql += "ORDER BY p.price DESC "; break;
                    case "price-asc":  sql += "ORDER BY p.price ASC "; break;
                    case "sold":       sql += "ORDER BY p.sold DESC "; break;
                    case "rating":     sql += "ORDER BY avg_rating DESC "; break;
                    default:           sql += "ORDER BY p.name ASC "; break;
                }
            } else {
                sql += "ORDER BY p.name ASC ";
            }

            sql += "LIMIT 25 OFFSET :offset";

            var query = handle.createQuery(sql);
            if (cid > 0) {
                query.bind("cid", cid);
            }
            query.bind("offset", offset);

            return query.mapToBean(Product.class).list();
        });
    }

    // Insert sản phẩm
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

    // Insert ảnh sản phẩm
    public void insertProductImage(int productId, String imageUrl) {
        getJdbi().useHandle(handle ->
                handle.createUpdate("INSERT INTO product_images (product_id, image_url) VALUES (:pid, :url)")
                        .bind("pid", productId)
                        .bind("url", imageUrl)
                        .execute()
        );
    }

    public boolean deleteProduct(int id) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate("UPDATE products SET state = 'Deleted' WHERE id = :id")
                        .bind("id", id)
                        .execute() > 0
        );
    }
    // Trong file ProductDao.java

    public boolean updateProduct(Product p) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate("UPDATE products SET " +
                                "name = :name, " +
                                "category_id = :catId, " +
                                "price = :price, " +
                                "stock = :stock, " +
                                "weight_grams = :weight, " +
                                "description = :desc, " +
                                "state = :state " +  // Lưu ý: Không có dấu phẩy ở dòng cuối cùng này
                                "WHERE id = :id")    // Lưu ý: Có dấu cách trước WHERE
                        .bind("name", p.getName())
                        .bind("catId", p.getCategory_id())
                        .bind("price", p.getPrice())
                        .bind("stock", p.getStock())
                        .bind("weight", p.getWeight_grams())
                        .bind("desc", p.getDescription())
                        .bind("state", p.getState())
                        .bind("id", p.getId())
                        .execute() > 0
        );
    }
}