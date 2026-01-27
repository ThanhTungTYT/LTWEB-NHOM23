package com.example.ltwebnhom23.controller.adminPage4;

import com.example.ltwebnhom23.model.User;
import com.example.ltwebnhom23.services.AccountService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UpdateUser", value = "/update-user")
public class UpdateUser extends HttpServlet {

    private AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int uid = Integer.parseInt(request.getParameter("uid"));

        User u = new User();
        u.setRole(request.getParameter("up_role"));

        if(accountService.updateUser(uid, u)){
            response.sendRedirect(request.getContextPath() + "/admin/users");
        }

    }
}