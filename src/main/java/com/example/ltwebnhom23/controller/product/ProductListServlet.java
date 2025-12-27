package com.example.ltwebnhom23.controller.product;
import com.example.ltwebnhom23.model.Category;
import com.example.ltwebnhom23.model.Product;
import com.example.ltwebnhom23.services.CategoryService;
import com.example.ltwebnhom23.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "catalog", value = "/catalog")
public class ProductListServlet extends HttpServlet {

    private CategoryService catalogService = new CategoryService();
    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> listCategories = catalogService.getAllCategories();
        request.setAttribute("listCategories", listCategories);

        String cidStr = request.getParameter("cid");
        String sort = request.getParameter("sort"); // Lấy kiểu sắp xếp

        int cid = 0;
        if (cidStr != null && !cidStr.isEmpty()) {
            try {
                cid = Integer.parseInt(cidStr);
            } catch (NumberFormatException e) {
                cid = 0;
            }
        }

        List<Product> listProducts = productService.getProductsForCatalog(cid, sort);

        request.setAttribute("listProducts", listProducts);

        request.setAttribute("currentCid", cid);
        request.setAttribute("currentSort", sort);

        request.getRequestDispatcher("catalog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}