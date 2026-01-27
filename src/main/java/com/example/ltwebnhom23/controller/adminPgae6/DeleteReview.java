package com.example.ltwebnhom23.controller.adminPgae6;

import com.example.ltwebnhom23.services.ReviewService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteReview", value = "/delete-review")
public class DeleteReview extends HttpServlet {

    private ReviewService review = new ReviewService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int rid = Integer.parseInt(request.getParameter("rid"));

        if(review.deleteReview(rid)){
            response.sendRedirect(request.getContextPath() + "/admin/reviews");
        }
    }
}