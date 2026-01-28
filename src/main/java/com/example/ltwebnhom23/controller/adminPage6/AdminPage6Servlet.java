package com.example.ltwebnhom23.controller.adminPage6;

import com.example.ltwebnhom23.model.ProductReview;
import com.example.ltwebnhom23.services.ReviewService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminPage6Servlet", value = "/admin/reviews")
public class AdminPage6Servlet extends HttpServlet {

    private ReviewService review = new ReviewService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductReview> listReview = review.getAllReview();

        request.setAttribute("listReview", listReview);

        request.getRequestDispatcher("/adminPage6.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}