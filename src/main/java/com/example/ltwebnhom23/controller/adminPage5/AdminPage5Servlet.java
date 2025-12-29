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
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        // 1. Lấy danh sách tất cả liên hệ từ Database
        List<Contact> contactList = contactDao.getAllContacts();

        if ((startDate != null && !startDate.isEmpty()) || (endDate != null && !endDate.isEmpty())) {
            contactList = contactDao.filterContacts(startDate, endDate);
        } else {
            contactList = contactDao.getAllContacts();
        }

        // 2. Gán vào request để JSP sử dụng
        request.setAttribute("contactList", contactList);

        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);

        // 3. Chuyển hướng sang trang JSP
        request.getRequestDispatcher("/adminPage5.jsp").forward(request, response);
    }
}
