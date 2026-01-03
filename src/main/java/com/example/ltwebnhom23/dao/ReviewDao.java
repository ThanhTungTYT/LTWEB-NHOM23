package com.example.ltwebnhom23.dao;

import com.example.ltwebnhom23.model.ProductReview;

import java.util.List;

public class ReviewDao extends BaseDao {

    public boolean addReview(ProductReview review){
        return getJdbi().withHandle(handle ->
            handle.createUpdate(" INSERT INTO products_review( product_id, user_id, rating, comment, created_at) \n" +
                    "VALUES(:product_id, :user_id, :rating, :comment, NOW());")
                    .bind("product_id", review.getProductId())
                    .bind("user_id", review.getUserId())
                    .bind("rating", review.getRating())
                    .bind("comment", review.getComment())
                    .execute() > 0
        );
    }

    public List<ProductReview> getReviewForProduct(int pid){
        return getJdbi().withHandle(handle ->
            handle.createQuery("SELECT r.*, u.full_name AS username FROM products_review r JOIN users u ON r.user_id = u.id WHERE product_id = :pid")
                    .bind("pid", pid)
                    .mapToBean(ProductReview.class)
                    .list()
        );
    }
}
