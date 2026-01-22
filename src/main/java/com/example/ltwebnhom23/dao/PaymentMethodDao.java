package com.example.ltwebnhom23.dao;

public class PaymentMethodDao extends  BaseDao{
    public Integer getIdByName(String name) {
        return getJdbi().withHandle(handle ->
                handle.createQuery(
                                "SELECT id FROM payment_method WHERE name = :name"
                        )
                        .bind("name", name)
                        .mapTo(Integer.class)
                        .findOne()
                        .orElse(null)
        );
    }

}
