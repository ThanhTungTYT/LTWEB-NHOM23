package com.example.ltwebnhom23.dao;

import com.example.ltwebnhom23.cart.Cart;
import com.example.ltwebnhom23.cart.CartItem;
import com.example.ltwebnhom23.model.Order;
import com.example.ltwebnhom23.model.OrderItem;
import com.example.ltwebnhom23.model.Product;

import java.util.List;

public class OrderDao extends BaseDao {

    public boolean createOrder(Order order, Cart checkoutCart) {
        return getJdbi().inTransaction(h -> {
            try {
                int orderId = h.createUpdate(
                                "INSERT INTO orders (" +
                                        "user_id, payment_method_id, promo_id, " +
                                        "receiver_name, receiver_phone, note, " +
                                        "total_amount, shipping_fee, discount_percent, final_amount, created_at, status" +
                                        ") VALUES (" +
                                        ":userId, :paymentMethodId, :promoId, " +
                                        ":receiverName, :receiverPhone, :note, " +
                                        ":totalAmount, :shippingFee, :discountPercent, :finalAmount, :createdAt, :status)" // Thêm status
                        ).bindBean(order)
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class).one();

                var batch = h.prepareBatch(
                        "INSERT INTO order_items(order_id, product_id, price, quantity) " +
                                "VALUES (:orderId, :productId, :price, :quantity)"
                );

                for (CartItem i : checkoutCart.getList()) {

                    batch.bind("orderId", orderId)
                            .bind("productId", i.getProduct().getId())
                            .bind("price", i.getPrice())
                            .bind("quantity", i.getQuantity())
                            .add();

                    int updateProduct = h.createUpdate(
                                    "UPDATE products " +
                                            "SET stock = stock - :qty, sold = sold + :qty " +
                                            "WHERE id = :pid AND stock >= :qty AND state = 'active'")
                            .bind("qty", i.getQuantity())
                            .bind("pid", i.getProduct().getId())
                            .execute();

                    if (updateProduct == 0) {
                        throw new RuntimeException("Sản phẩm " + i.getProduct().getName() + " đã hết hàng hoặc không đủ số lượng.");
                    }

                    h.createUpdate("UPDATE products SET state = 'inactive' WHERE id = :pid AND stock <= 0")
                            .bind("pid", i.getProduct().getId())
                            .execute();
                }
                batch.execute();

                if (order.getPromoId() != null && order.getPromoId() > 0) {
                    int updatePromo = h.createUpdate(
                            "UPDATE promotions " +
                                    "SET quantity = quantity - 1 " +
                                    "WHERE id = :pid AND quantity > 0 AND state = 'active'"
                    ).bind("pid", order.getPromoId()).execute();

                }

                return true;
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        });
    }
    public List<Order> getAllOrders() {
        return getJdbi().withHandle(h ->
                h.createQuery(
                                "SELECT id, user_id, payment_method_id, promo_id, " +
                                        "receiver_name, receiver_phone, note, " +
                                        "total_amount, shipping_fee, discount_percent, final_amount, " +
                                        "status, created_at " +
                                        "FROM orders " +
                                        "ORDER BY created_at DESC"
                        )
                        .mapToBean(Order.class)
                        .list()
        );
    }
    /* ================== GET ORDERS BY USER ================== */
    public List<Order> getOrdersByUserId(int userId) {
        return getJdbi().withHandle(h ->
                h.createQuery(
                                "SELECT id, user_id, payment_method_id, promo_id, " +
                                        "receiver_name, receiver_phone, note, " +
                                        "total_amount, shipping_fee, discount_percent, final_amount, " +
                                        "status, created_at " +
                                        "FROM orders " +
                                        "WHERE user_id = :uid " +
                                        "ORDER BY created_at DESC"
                        )
                        .bind("uid", userId)
                        .mapToBean(Order.class)
                        .list()
        );
    }

    /* ================== GET ITEMS BY ORDER ================== */
    public List<OrderItem> getItemsByOrderId(int orderId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery(
                                "SELECT " +
                                        " oi.id, oi.order_id, oi.product_id, oi.quantity, oi.price, " +
                                        " p.name AS product_name, " +
                                        " ( " +
                                        "   SELECT pi.image_url " +
                                        "   FROM product_images pi " +
                                        "   WHERE pi.product_id = p.id " +
                                        "   ORDER BY pi.id " +
                                        "   LIMIT 1 " +
                                        " ) AS image_url " +
                                        "FROM order_items oi " +
                                        "JOIN products p ON oi.product_id = p.id " +
                                        "WHERE oi.order_id = :oid"
                        )
                        .bind("oid", orderId)
                        .map((rs, ctx) -> {
                            Product product = new Product();
                            product.setId(rs.getInt("product_id"));
                            product.setName(rs.getString("product_name"));
                            product.setImage_url(rs.getString("image_url"));

                            OrderItem item = new OrderItem();
                            item.setId(rs.getInt("id"));
                            item.setOrderId(rs.getInt("order_id"));
                            item.setProductId(rs.getInt("product_id"));
                            item.setQuantity(rs.getInt("quantity"));
                            item.setPrice(rs.getDouble("price"));
                            item.setProduct(product);

                            return item;
                        })
                        .list()
        );
    }
    public boolean updateOrderStatus(Order order) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate(
                                "UPDATE orders " +
                                        "SET status = :status " +
                                        "WHERE id = :orderId"
                        )
                        .bind("status", order.getStatus())
                        .bind("orderId", order.getId())
                        .execute() > 0
        );
    }
    public boolean cancelOrder(Order order) {
        return getJdbi().withHandle(handle -> {
            int rows = handle.createUpdate("UPDATE orders SET status = :status WHERE id = :id")
                    .bind("status", order.getStatus())
                    .bind("id", order.getId())
                    .execute();

            return rows > 0;
        });
    }
}
