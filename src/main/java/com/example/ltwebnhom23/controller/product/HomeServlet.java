package com.example.ltwebnhom23.controller.product;

import com.example.ltwebnhom23.model.Banner;
import com.example.ltwebnhom23.model.Product;
import com.example.ltwebnhom23.services.BannerService;
import com.example.ltwebnhom23.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "")
public class HomeServlet extends HttpServlet {

    private ProductService productService = new ProductService();
    private BannerService bannerService = new BannerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> listProduct = productService.getProductsBySold();
        List<Banner> listBanner = bannerService.getBannerActive();

        request.setAttribute("listProduct", listProduct);
        request.setAttribute("listBanner", listBanner);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}