package com.example.ltwebnhom23.controller.product;
import com.example.ltwebnhom23.model.ProductReview;
import com.example.ltwebnhom23.model.User;
import com.example.ltwebnhom23.services.ReviewService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddReview", value = "/addReview")
public class AddReview extends HttpServlet {

    private ReviewService reviewService = new ReviewService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        int productId = Integer.parseInt(request.getParameter("pid"));
        if (user == null) {
            session.setAttribute("reviewNotice", "Bạn cần đăng nhập để gửi đánh giá");
            response.sendRedirect(
                    request.getContextPath() + "/product?pid=" + productId
            );
            return;
        }

        ProductReview review = new ProductReview();
        review.setProductId(productId);
        review.setUserId(user.getId());
        review.setRating(Integer.parseInt(request.getParameter("rating")));
        review.setComment(request.getParameter("comment"));

        if (!reviewService.addReview(review)) {
            session.setAttribute("reviewNotice", "Đã xảy ra lỗi!");
        } else {
            session.setAttribute("reviewNotice", "Gửi đánh giá thành công");
        }

        response.sendRedirect(
                request.getContextPath() + "/product?pid=" + productId
        );
    }
}
