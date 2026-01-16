package com.example.ltwebnhom23.controller.adminPage7;
import com.example.ltwebnhom23.model.Banner;
import com.example.ltwebnhom23.services.BannerService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminPage7Servlet", value = "/adminPage7")
public class AdminPage7Servlet extends HttpServlet {

    private BannerService banner = new BannerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Banner> listBanner = banner.getAllBanner();
        request.setAttribute("listBanner", listBanner);

        request.getRequestDispatcher("adminPage7.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}