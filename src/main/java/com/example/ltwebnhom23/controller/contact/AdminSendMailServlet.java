package com.example.ltwebnhom23.controller.contact;

// CHÚ Ý DÒNG IMPORT NÀY:
import com.example.ltwebnhom23.services.ContactService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminSendMailServlet", urlPatterns = {"/admin-send-mail"})
public class AdminSendMailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String toEmail = request.getParameter("toEmail");
        String toName = request.getParameter("toName");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");

        String htmlContent = "<h3>Xin chào " + toName + ",</h3>"
                + "<p>Cảm ơn bạn đã liên hệ với Aroma Café. Đây là phản hồi từ chúng tôi:</p>"
                + "<div style='background-color: #f0f0f0; padding: 15px; border-left: 5px solid #c76739; margin: 10px 0;'>"
                + content.replace("\n", "<br>")
                + "</div>"
                + "<p>Trân trọng,<br><b>Aroma Café Support Team</b></p>";

        HttpSession session = request.getSession();

        try {
            ContactService.sendEmail(toEmail, subject, htmlContent);

            session.setAttribute("msgSuccess", "Đã gửi phản hồi thành công tới " + toEmail);
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("msgError", "Gửi thất bại: " + e.getMessage());
        }

        // Quay về trang quản lý
        response.sendRedirect(request.getContextPath() + "/adminPage5");
    }
}