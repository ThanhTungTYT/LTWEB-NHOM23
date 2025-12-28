package com.example.ltwebnhom23.controller.account;

import com.example.ltwebnhom23.model.User;
import com.example.ltwebnhom23.model.Address;
import com.example.ltwebnhom23.services.AccountService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AccountServlet", urlPatterns = {"/account", "/info", "/update-info"})
public class AccountServlet extends HttpServlet {

    private AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        HttpSession session = request.getSession();
        User authUser = (User) session.getAttribute("user");

        // Kiểm tra đăng nhập chung
        if (authUser == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // TRƯỜNG HỢP 1: Header gọi vào đây -> Trả về trang khung (Layout)
        if (action.equals("/account")) {
            request.getRequestDispatcher("/account.jsp").forward(request, response);
        }

        // TRƯỜNG HỢP 2: AJAX gọi vào đây -> Trả về nội dung form (Fragment)
        else if (action.equals("/info")) {
            User userDetail = accountService.getAccountInfo(authUser.getId());
            Address addressDetail = accountService.getUserAddress(authUser.getId());

            request.setAttribute("user", userDetail);
            request.setAttribute("addr", addressDetail);

            request.getRequestDispatcher("/info.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        if ("/update-info".equals(action)) {
            HttpSession session = request.getSession();
            User authUser = (User) session.getAttribute("user");

            if (authUser != null) {
                // ... (Code update giữ nguyên như cũ của bạn) ...
                String fullName = request.getParameter("fullname");
                String phone = request.getParameter("phone");
                String city = request.getParameter("city");
                String district = request.getParameter("district");
                String streetAddress = request.getParameter("address");
                String addressIdStr = request.getParameter("addressId");
                int addressId = (addressIdStr != null && !addressIdStr.isEmpty()) ? Integer.parseInt(addressIdStr) : 0;

                boolean isUpdated = accountService.updateUserInfo(authUser.getId(), fullName, phone, addressId, city, district, streetAddress);

                if (isUpdated) {
                    authUser.setFull_name(fullName);
                    authUser.setPhone(phone);
                    session.setAttribute("user", authUser);
                    request.setAttribute("message", "Cập nhật thành công!");
                } else {
                    request.setAttribute("error", "Cập nhật thất bại!");
                }

                User userDetail = accountService.getAccountInfo(authUser.getId());
                Address addressDetail = accountService.getUserAddress(authUser.getId());
                request.setAttribute("user", userDetail);
                request.setAttribute("addr", addressDetail);

                // Quan trọng: Trả về info.jsp để AJAX cập nhật lại vùng nội dung
                request.getRequestDispatcher("/info.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        }
    }
}