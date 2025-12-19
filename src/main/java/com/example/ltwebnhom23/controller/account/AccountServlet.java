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

@WebServlet(name = "AccountServlet", urlPatterns = "/info")
public class AccountServlet extends HttpServlet {

    private AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        // Key "auth" phải khớp với key bạn lưu khi đăng nhập
        User authUser = (User) session.getAttribute("user");

        if (authUser != null) {
            // Lấy thông tin mới nhất từ DB
            User userDetail = accountService.getAccountInfo(authUser.getId());
            Address addressDetail = accountService.getUserAddress(authUser.getId());

            // Đặt tên attribute là "user" để info.jsp dùng được ${user.email}
            request.setAttribute("user", userDetail);
            request.setAttribute("addr", addressDetail);

            // Đường dẫn tới file info.jsp bạn đang có (kiểm tra lại vị trí thực tế của file)
            request.getRequestDispatcher("/info.jsp").forward(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("<p style='color:red'>Vui lòng đăng nhập lại.</p>");
        }
    }
}