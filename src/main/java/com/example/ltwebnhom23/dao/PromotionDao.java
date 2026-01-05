package com.example.ltwebnhom23.dao;

import com.example.ltwebnhom23.model.Promotion;
import java.util.List;

public class PromotionDao extends BaseDao {

    public List<Promotion> getAll() {
        return getJdbi().withHandle(handle ->
                handle.createQuery("SELECT id, code, description, " +
                                "discount_percent AS discountPercent, " +
                                "min_order_value AS minOrderValue, " +
                                "start_date AS startDate, " +
                                "end_date AS endDate, " +
                                "quantity " +
                                "FROM promotions ORDER BY id DESC")
                        .mapToBean(Promotion.class)
                        .list()
        );
    }
}