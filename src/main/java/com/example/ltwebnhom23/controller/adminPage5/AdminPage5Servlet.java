package com.example.ltwebnhom23.controller.adminPage5;
import com.example.ltwebnhom23.dao.ContactDao;
import com.example.ltwebnhom23.model.Contact;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminContactServlet", urlPatterns = {"/adminPage5"})
public class AdminPage5Servlet extends HttpServlet {

    private ContactDao contactDao;

    @Override
    public void init() {
        contactDao = new ContactDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Lấy tham số lọc từ URL
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        // 2. Cấu hình phân trang
        int page = 1;
        int pageSize = 25; // Hiển thị 25 dòng mỗi trang

        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
                if (page < 1) page = 1;
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        // Tính vị trí bắt đầu lấy dữ liệu trong database
        int offset = (page - 1) * pageSize;

        // 3. Gọi DAO để lấy dữ liệu
        // - Lấy tổng số lượng tin nhắn (khớp với điều kiện lọc) để tính số trang
        int totalContacts = contactDao.countContacts(startDate, endDate);

        // - Lấy danh sách tin nhắn cho trang hiện tại (có limit và offset)
        List<Contact> contactList = contactDao.getContacts(startDate, endDate, pageSize, offset);

        // 4. Tính tổng số trang
        // Math.ceil: làm tròn lên (ví dụ có 26 tin, mỗi trang 25 tin -> cần 2 trang)
        int totalPages = (int) Math.ceil((double) totalContacts / pageSize);

        // 5. Gán dữ liệu vào request để JSP hiển thị
        request.setAttribute("contactList", contactList);   // Danh sách tin nhắn
        request.setAttribute("currentPage", page);          // Trang hiện tại
        request.setAttribute("totalPages", totalPages);     // Tổng số trang

        // Gửi lại ngày lọc để giữ trạng thái trên ô input
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);

        // 6. Chuyển hướng sang trang JSP
        request.getRequestDispatcher("/adminPage5.jsp").forward(request, response);
    }
}
