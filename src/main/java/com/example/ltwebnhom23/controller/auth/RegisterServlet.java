package com.example.ltwebnhom23.controller.auth;

import com.example.ltwebnhom23.model.User;
import com.example.ltwebnhom23.services.AuthService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "register", value = "/register")
public class RegisterServlet extends HttpServlet {

    private AuthService authService = new AuthService();
    private Validation validation = new Validation();
    private static final int LENGTH = 8;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if(authService.existsByEmail(request.getParameter("email"))){
            request.setAttribute("status", "Email đã được sử dụng");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if(!validation.rePass(request.getParameter("password"), request.getParameter(confirmpassword))){
            request.setAttribute("status", "Xác nhận mật khẩu không chính xác");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if(!validation.isEmail(request.getParameter(email))){
            request.setAttribute("status", "Email không đúng định dạng");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if(!validation.isPhone(request.getParameter("phone"))){
            request.setAttribute("status", "Số điện thoại không chính xác");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if(!validation.passLength(request.getParameter("password"), LENGTH)){
            request.setAttribute("status", "Mật khẩu phải có ít nhất" + LENGTH + "kí tự");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if(!validation.containChar(request.getParameter("password"))){
            request.setAttribute("status", "Mật khẩu phải chứa ít nhất 1 kí tự đặc biệt, chữ in hoa và sốs");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setFull_name(request.getParameter("fullname"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        user.setPassword_hash(request.getParameter("password"));

        boolean status = authService.register(user);

        if(status){
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }else {
            request.setAttribute("status", "Đăng kí không thành công");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}