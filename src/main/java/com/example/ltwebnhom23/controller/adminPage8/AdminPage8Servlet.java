package com.example.ltwebnhom23.controller.adminPage8;

import com.example.ltwebnhom23.model.Promotion;
import com.example.ltwebnhom23.services.PromotionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet(name = "AdminPage8Servlet", urlPatterns = {"/adminPage8"})
public class AdminPage8Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if ("delete".equals(action) && idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                PromotionService.getInstance().deletePromotion(id);
                response.sendRedirect(request.getContextPath() + "/adminPage8");
                return;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        PromotionService.getInstance().autoUpdateStatus();

        String keyword = request.getParameter("search");
        List<Promotion> listPromotions;

        if (keyword != null && !keyword.trim().isEmpty()) {
            listPromotions = PromotionService.getInstance().searchPromotions(keyword.trim());
        } else {
            listPromotions = PromotionService.getInstance().getAllPromotions();
        }

        request.setAttribute("listPromotions", listPromotions);
        request.setAttribute("searchKeyword", keyword);

        request.getRequestDispatcher("/adminPage8.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action"); // "add" hoặc "update"
        String error = null;

        // 1. Lấy dữ liệu từ form
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        String minOrderStr = request.getParameter("minOrderValue");
        String discountStr = request.getParameter("discountPercent");
        String quantityStr = request.getParameter("quantity");
        String startStr = request.getParameter("startDate");
        String endStr = request.getParameter("endDate");
        String state = request.getParameter("state");
        String idStr = request.getParameter("id");

        // 2. Validate dữ liệu
        try {
            // Chuyển đổi số
            double minOrder = Double.parseDouble(minOrderStr);
            double discount = Double.parseDouble(discountStr);
            int quantity = Integer.parseInt(quantityStr);

            // Chuyển đổi ngày (Form gửi về yyyy-MM-dd)
            LocalDate startDate = LocalDate.parse(startStr);
            LocalDate endDate = LocalDate.parse(endStr);
            LocalDate today = LocalDate.now();

            // --- BẮT ĐẦU KIỂM TRA ĐIỀU KIỆN ---

            // Check 1: Mã code không được trùng (chỉ check khi action là add)
            if ("add".equals(action) && PromotionService.getInstance().isCodeExist(code)) {
                error = "Mã giảm giá '" + code + "' đã tồn tại!";
            }
            // Check 2: Điều kiện tối thiểu là 1đ
            else if (minOrder < 1) {
                error = "Đơn hàng tối thiểu phải từ 1đ trở lên!";
            }
            // Check 3: Mức giảm từ 1% đến 100%
            else if (discount < 1 || discount > 100) {
                error = "Mức giảm giá phải từ 1% đến 100%!";
            }
            // Check 4: Số lượng từ 1 đến 1,000,000
            else if (quantity < 1 || quantity > 1000000) {
                error = "Số lượng mã phải từ 1 đến 1.000.000!";
            }
            // Check 5: Ngày bắt đầu tối thiểu là hiện tại
            else if (startDate.isBefore(today)) {
                error = "Ngày bắt đầu không được ở quá khứ!";
            }
            // Check 6: Ngày kết thúc tối thiểu là ngày mai (lớn hơn today)
            else if (!endDate.isAfter(today)) {
                error = "Ngày kết thúc phải từ ngày mai trở đi!";
            }
            else if (endDate.isBefore(startDate)) {
                error = "Ngày kết thúc không được nhỏ hơn ngày bắt đầu!";
            }
            // Check 7: Trạng thái
            else if (!"active".equals(state) && !"inactive".equals(state)) {
                error = "Trạng thái không hợp lệ!";
            }

            // --- NẾU CÓ LỖI ---
            if (error != null) {
                request.setAttribute("errorMessage", error);
                // Giữ lại dữ liệu cũ để người dùng không phải nhập lại (Optional - cần sửa JSP để nhận)
                doGet(request, response); // Quay lại trang hiển thị kèm thông báo lỗi
                return;
            }

            // --- NẾU KHÔNG CÓ LỖI -> THỰC HIỆN INSERT/UPDATE ---
            Promotion p = new Promotion();
            p.setCode(code);
            p.setDescription(description);
            p.setMinOrderValue(minOrder);
            p.setDiscountPercent(discount);
            p.setQuantity(quantity);
            p.setStartDate(Timestamp.valueOf(startDate.atStartOfDay())); // Chuyển LocalDate sang Timestamp
            p.setEndDate(Timestamp.valueOf(endDate.atStartOfDay()));
            p.setState(state);

            if ("add".equals(action)) {
                PromotionService.getInstance().addPromotion(p);
            }


            // Thành công -> Redirect để tránh resubmit form
            response.sendRedirect(request.getContextPath() + "/adminPage8");

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Dữ liệu số không hợp lệ!");
            doGet(request, response);
        } catch (DateTimeParseException e) {
            request.setAttribute("errorMessage", "Định dạng ngày tháng không hợp lệ!");
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi hệ thống: " + e.getMessage());
            doGet(request, response);
        }
    }
}