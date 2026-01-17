package com.example.ltwebnhom23.dao;

import com.example.ltwebnhom23.cart.Cart;
import com.example.ltwebnhom23.cart.CartItem;
import com.example.ltwebnhom23.model.Order;

public class OrderDao extends BaseDao {

    public boolean createOrder(Order order, Cart cart) {
        return getJdbi().inTransaction(h -> {
            int orderId = h.createUpdate(
                            "INSERT INTO orders(user_id,promo_id,total,discount,final_amount,created_at) " +
                                    "VALUES(:userId,:promoId,:total,:discount,:finalAmount,:createdAt)"
                    ).bindBean(order)
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class).one();

            var batch = h.prepareBatch(
                    "INSERT INTO order_details(order_id,product_id,price,quantity) " +
                            "VALUES(:oid,:pid,:price,:qty)"
            );

            for (CartItem i : cart.getList()) {
                batch.bind("oid", orderId)
                        .bind("pid", i.getProduct().getId())
                        .bind("price", i.getPrice())
                        .bind("qty", i.getQuantity())
                        .add();
            }
            batch.execute();
            return true;
        });
    }
}
