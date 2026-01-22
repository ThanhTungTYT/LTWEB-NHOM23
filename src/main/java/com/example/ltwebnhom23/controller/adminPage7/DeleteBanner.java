package com.example.ltwebnhom23.controller.adminPage7;

import com.example.ltwebnhom23.services.BannerService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteBanner", value = "/delete-banner")
public class DeleteBanner extends HttpServlet {

    private BannerService banner = new BannerService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int bid = Integer.parseInt(request.getParameter("bid"));

        if(banner.deleteBanner(bid)){
            session.setAttribute("noticeDel", "Xóa thành công");
        }else {
            session.setAttribute("noticeDel", "Xóa thất bại");
        }

        response.sendRedirect(request.getContextPath() + "/adminPage7");
    }
}