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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(authService.existsByEmail(request.getParameter("email"))){
            request.setAttribute("status", "Email đã được sử dụng");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if(!request.getParameter("password").equals(request.getParameter("confirmpassword"))){
            request.setAttribute("status", "Xác nhận mật khẩu không chính xác");
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
            request.setAttribute("status", "Đăng kí thành công");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }else {
            request.setAttribute("status", "Đăng kí không thành công");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
    }
}