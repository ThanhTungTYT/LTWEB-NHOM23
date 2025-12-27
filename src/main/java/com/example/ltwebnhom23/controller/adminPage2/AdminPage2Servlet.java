package com.example.ltwebnhom23.controller.adminPage2;

import com.example.ltwebnhom23.model.Product;
import com.example.ltwebnhom23.services.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminPage2Servlet", value = "/adminPage2")

public class AdminPage2Servlet  extends HttpServlet {
    private ProductService productService = new ProductService();

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.getAllProduct();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/adminPage2.jsp").forward(request,  response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            int categoryId = Integer.parseInt(request.getParameter("category_id"));
            int weight = Integer.parseInt(request.getParameter("weight"));


            String[] imageUrls = request.getParameterValues("image_urls");

            Product newProduct = new Product();
            newProduct.setName(name);
            newProduct.setDescription(description);
            newProduct.setPrice(price);
            newProduct.setStock(stock);
            newProduct.setCategory_id(categoryId);
            newProduct.setWeight_grams(weight);
            newProduct.setSold(0);

            boolean isSuccess = productService.addProductWithUrls(newProduct, imageUrls);

            if (isSuccess) {
                response.sendRedirect(request.getContextPath() + "/adminPage2?msg=success");
            } else {
                request.setAttribute("error", "Thêm sản phẩm thất bại!");
                doGet(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi: " + e.getMessage());
            doGet(request, response);
        }
    }
}
