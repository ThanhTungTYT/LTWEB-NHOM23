package com.example.ltwebnhom23.controller.product;

import com.example.ltwebnhom23.model.Product;
import com.example.ltwebnhom23.model.ProductImage;
import com.example.ltwebnhom23.services.ImageService;
import com.example.ltwebnhom23.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "product", value = "/product")
public class DetailProductServlet extends HttpServlet {

    private ProductService productService = new ProductService();
    private ImageService imageService = new ImageService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        int productId = Integer.parseInt(pid);

        Product product = productService.getProduct(productId);
        request.setAttribute("product", product);

        List<Product> relative = productService.getProductsByRelative(product.getCategory_id(), product.getName(), product.getId());
        request.setAttribute("relative", relative);

        List<ProductImage> listImage = imageService.getAllImageById(productId);
        request.setAttribute("listImage", listImage);

        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}