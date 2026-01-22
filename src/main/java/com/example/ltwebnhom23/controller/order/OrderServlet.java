package com.example.ltwebnhom23.controller.order;

import com.example.ltwebnhom23.cart.Cart;
import com.example.ltwebnhom23.model.Order;
import com.example.ltwebnhom23.model.Promotion;
import com.example.ltwebnhom23.model.User;
import com.example.ltwebnhom23.services.OrderService;
import com.example.ltwebnhom23.services.PaymentMethodService;
import com.example.ltwebnhom23.services.PromotionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;

import static com.example.ltwebnhom23.services.PaymentMethodService.*;

@WebServlet("/payment")
public class OrderServlet extends HttpServlet {

    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("cart") == null) {
            resp.sendRedirect("cart");
            return;
        }

        req.setAttribute("cart", s.getAttribute("cart"));
        req.setAttribute(
                "promotions",
                PromotionService.getInstance().getAvailablePromotions()
        );

        req.getRequestDispatcher("payment.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession s = req.getSession(false);
        if (s == null || s.getAttribute("user") == null || s.getAttribute("cart") == null) {
            resp.sendRedirect("login");
            return;
        }

        User user = (User) s.getAttribute("user");
        Cart cart = (Cart) s.getAttribute("cart");

        double total = cart.getTotal();
        double shippingFee = 30000;
        double discountPercent = 0;

        Promotion promotion = null;
        String pid = req.getParameter("promotionId");
        String paymentName = req.getParameter("paymentMethod"); // COD | BANK
        int paymentMethodId = PaymentMethodService.getInstance()
                .getPaymentMethodId(paymentName);
        String receiverName = req.getParameter("fullname");
        String receiverPhone = req.getParameter("phone");
        String note = req.getParameter("note");
        if (note == null || note.isBlank()) {
            note = "Không có ghi chú";
        }

        // ===== CÓ CHỌN MÃ =====
        if (pid != null && !pid.isBlank()) {
            promotion = PromotionService.getInstance()
                    .getPromotionById(Integer.parseInt(pid));

            if (promotion != null
                    && "active".equals(promotion.getState())
                    && promotion.getQuantity() > 0
                    && total >= promotion.getMinOrderValue()) {

                discountPercent = promotion.getDiscountPercent();

                if (discountPercent < 0) discountPercent = 0;
                if (discountPercent > 100) discountPercent = 100;
            } else {
                promotion = null;
            }
        }

        // ===== KHÔNG CHỌN / MÃ KHÔNG HỢP LỆ =====
        if (promotion == null) {
            promotion = PromotionService.getInstance().getNoPromo();
            discountPercent = 0;
        }

        double discountAmount = total * discountPercent / 100;
        double finalAmount = total - discountAmount + shippingFee;

        // ===== TẠO ORDER =====
        Order order = new Order();
        order.setUserId(user.getId());
        order.setPaymentMethodId(paymentMethodId);
        order.setPromoId(promotion.getId());
        order.setReceiverName(receiverName);
        order.setReceiverPhone(receiverPhone);
        order.setTotalAmount(total);
        order.setShippingFee(shippingFee);
        order.setDiscountPercent(discountPercent);
        order.setFinalAmount(finalAmount);
        order.setNote(note);
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        orderService.create(order, cart);
        s.removeAttribute("cart");
        resp.sendRedirect(req.getContextPath() + "/account");
    }
}