package com.example.ltwebnhom23.dao;

import com.example.ltwebnhom23.cart.Cart;
import com.example.ltwebnhom23.cart.CartItem;
import com.example.ltwebnhom23.model.Order;

public class OrderDao extends BaseDao {

    public boolean createOrder(Order order, Cart cart) {
        return getJdbi().inTransaction(h -> {
            int orderId = h.createUpdate(
                            "INSERT INTO orders (" +
                                    "user_id, payment_method_id, promo_id, " +
                                    "receiver_name, receiver_phone, note, " +
                                    "total_amount, shipping_fee, discount_percent, final_amount, created_at" +
                                    ") VALUES (" +
                                    ":userId, :paymentMethodId, :promoId, " +
                                    ":receiverName, :receiverPhone, :note, " +
                                    ":totalAmount, :shippingFee, :discountPercent, :finalAmount, :createdAt)"
                    ).bindBean(order)
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class).one();

            var batch = h.prepareBatch(
                    "INSERT INTO order_items(order_id, product_id, price, quantity) " +
                            "VALUES (:orderId, :productId, :price, :quantity)"
            );

            for (CartItem i : cart.getList()) {
                batch
                    .bind("orderId", orderId)
                    .bind("productId", i.getProduct().getId())
                    .bind("price", i.getPrice())
                    .bind("quantity", i.getQuantity())
                    .add();
            }
            batch.execute();
            return true;
        });
    }
}
