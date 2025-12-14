package com.example.ltwebnhom23.controller.product;
import com.example.ltwebnhom23.model.Category;
import com.example.ltwebnhom23.model.Product;
import com.example.ltwebnhom23.services.CategoryService;
import com.example.ltwebnhom23.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "catalog", value = "/catalog")
public class ProductListServlet extends HttpServlet {

    private CategoryService catalogService = new CategoryService();
    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> listCategories = catalogService.getAllCategories();
        request.setAttribute("listCategories", listCategories);

        String cid = request.getParameter("cid");
        List<Product> listProducts = new ArrayList<>();

        if(cid == null || cid.equals("0")){
            listProducts = productService.getAllProduct();
        }else{
            try {
                int categoryId = Integer.parseInt(cid);
                listProducts = productService.getProductForCategory(categoryId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("listProducts", listProducts);

        request.getRequestDispatcher("catalog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
