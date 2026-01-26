package com.example.ltwebnhom23.controller.adminPgae6;

import com.example.ltwebnhom23.model.ProductReview;
import com.example.ltwebnhom23.services.ReviewService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchReview", value = "/search-review")
public class SearchReview extends HttpServlet {

    private ReviewService review = new ReviewService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductReview> listReview = new ArrayList<>();

        String key = request.getParameter("key");

        if(key == null || key.equals("")){
            listReview = review.getAllReview();
        }else{
            listReview = review.getReviewByKey(key);
        }

        request.setAttribute("listReview", listReview);

        request.getRequestDispatcher("adminPage6.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}