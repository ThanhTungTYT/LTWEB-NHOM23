package com.example.ltwebnhom23.controller.contact;

import com.example.ltwebnhom23.dao.ContactDao;
import com.example.ltwebnhom23.model.Contact;

import com.example.ltwebnhom23.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ContactController", urlPatterns = {"/contact"})
public class ContactController extends HttpServlet {

    private ContactDao contactDao;

    @Override
    public void init() {
        contactDao = new ContactDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User authUser = (User) session.getAttribute("user");

        if (authUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        request.getRequestDispatcher("/help.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        User authUser = (User) session.getAttribute("user");
        if (authUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String fullName = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        Contact contact = new Contact();
        contact.setUserId(authUser.getId());
        contact.setFullName(fullName);
        contact.setEmail(email);
        contact.setMessage(message);

        contactDao.insertContact(contact);

        request.setAttribute("success", "Gửi liên hệ thành công!");
        request.getRequestDispatcher("/help.jsp").forward(request, response);
    }

}
