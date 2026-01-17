package com.example.ltwebnhom23.controller.adminPage7;
import com.example.ltwebnhom23.model.Banner;
import com.example.ltwebnhom23.services.BannerService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "AddBanner", value = "/add-banner")
public class AddBanner extends HttpServlet {

    BannerService banner = new BannerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Banner b = new Banner();
        b.setBanner_url(request.getParameter("banner_url"));
        b.setStatus(request.getParameter("status"));
        b.setStartDateFromForm(request.getParameter("start"));
        b.setEndDateFromForm(request.getParameter("end"));

        if(banner.addBanner(b)){
            session.setAttribute("notice", "Thêm thành công");
        }else {
            session.setAttribute("notice", "Thêm thất bại");
        }

        response.sendRedirect(request.getContextPath() + "/adminPage7");
    }
}