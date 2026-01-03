package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.dao.ReviewDao;
import com.example.ltwebnhom23.model.ProductReview;

import java.util.List;

public class ReviewService {

    private ReviewDao reviewDao = new ReviewDao();

    public boolean addReview(ProductReview review){
        return reviewDao.addReview(review);
    }

    public List<ProductReview> getReviewForProduct(int pid){
        return reviewDao.getReviewForProduct(pid);
    }
}
