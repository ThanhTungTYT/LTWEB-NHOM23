package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.dao.PromotionDao;
import com.example.ltwebnhom23.model.Promotion;
import java.util.List;

public class PromotionService {
    private static PromotionService instance;
    private final PromotionDao promotionDao;

    private PromotionService() {
        promotionDao = new PromotionDao();
    }

    public static PromotionService getInstance() {
        if (instance == null) {
            instance = new PromotionService();
        }
        return instance;
    }

    public List<Promotion> getAllPromotions() {
        return promotionDao.getAll();
    }
}