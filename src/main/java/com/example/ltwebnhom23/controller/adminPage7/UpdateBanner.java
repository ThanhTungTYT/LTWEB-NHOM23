package com.example.ltwebnhom23.controller.adminPage7;

import com.example.ltwebnhom23.model.Banner;
import com.example.ltwebnhom23.services.BannerService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "UpdateBanner", value = "/update-banner")
public class UpdateBanner extends HttpServlet {

    private BannerService banner = new BannerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int bid = Integer.parseInt(request.getParameter("bid"));

        Banner b = new Banner();
        b.setBanner_url(request.getParameter("up_url"));
        b.setStatus(request.getParameter("up_status"));
        b.setStartDateFromForm(request.getParameter("up_start"));
        b.setEndDateFromForm(request.getParameter("up_end"));

        if(banner.updateBanner(bid, b)){
            session.setAttribute("notice_up", "Cập nhật thành công");
        }else{
            session.setAttribute("notice_up", "Cập nhật không thành công");
        }

        response.sendRedirect(request.getContextPath() + "/adminPage7");
    }
}