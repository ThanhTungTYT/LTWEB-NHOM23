package com.example.ltwebnhom23.controller.auth;

import com.example.ltwebnhom23.services.AuthService;
import com.example.ltwebnhom23.services.ContactService; // Import service gửi mail
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

        // Lấy dữ liệu từ form
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmpassword");

        // --- PHẦN VALIDATION (GIỮ NGUYÊN) ---
        if(authService.existsByEmail(email)){
            request.setAttribute("status", "Email đã được sử dụng");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if(!validation.rePass(password, confirmPassword)){
            request.setAttribute("status", "Xác nhận mật khẩu không chính xác");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if(!validation.isEmail(email)){
            request.setAttribute("status", "Email không đúng định dạng");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if(!validation.isPhone(phone)){
            request.setAttribute("status", "Số điện thoại không chính xác");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if(!validation.passLength(password, LENGTH)){
            request.setAttribute("status", "Mật khẩu phải có ít nhất " + LENGTH + " kí tự");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        if(!validation.containChar(password)){
            request.setAttribute("status", "Mật khẩu phải chứa ít nhất 1 kí tự đặc biệt, chữ in hoa và số");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // --- BẮT ĐẦU LOGIC OTP VÀ SESSION ---
        try {
            Random rnd = new Random();
            int number = rnd.nextInt(999999);
            String otp = String.format("%06d", number);

            String subject = "Mã xác thực đăng ký tài khoản";
            String body = "<h3>Xin chào " + fullname + ",</h3>" +
                    "<p>Mã xác thực (OTP) của bạn là: <strong style='color: #c76739; font-size: 18px;'>" + otp + "</strong></p>" +
                    "<p>Mã có hiệu lực trong 10 phút.</p>";

            ContactService.sendEmail(email, subject, body);

            Map<String, Object> regData = new HashMap<>();
            regData.put("fullname", fullname);
            regData.put("email", email);
            regData.put("phone", phone);
            regData.put("password", password); // Lưu password chưa hash
            regData.put("otp", otp);

            long currentTime = System.currentTimeMillis();
            regData.put("expireTime", currentTime + (10 * 60 * 1000));
            regData.put("nextResend", currentTime + (2 * 60 * 1000));

            HttpSession session = request.getSession();
            session.setAttribute("reg_temp", regData);

            response.sendRedirect(request.getContextPath() + "/verify-otp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status", "Lỗi hệ thống gửi mail: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}