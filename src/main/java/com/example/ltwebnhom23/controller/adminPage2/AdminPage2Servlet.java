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
import com.example.ltwebnhom23.services.CategoryService;
import com.example.ltwebnhom23.model.Category;
import com.example.ltwebnhom23.dao.CategoryDao;

@WebServlet(name = "AdminPage2Servlet", value = "/adminPage2")

public class AdminPage2Servlet  extends HttpServlet {
    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();
    private CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.getAllProduct();
        request.setAttribute("products", products);
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/adminPage2.jsp").forward(request,  response);
        ;    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "";

        try {
            switch (action) {
                case "add_category":
                    handleAddCategory(request, response);
                    break;
                case "delete_list":
                    handleDeleteList(request, response);
                    break;
                case "delete_category":
                    handleDeleteCategory(request, response);
                    break;
                case "edit_product":
                    handleEditProduct(request, response);
                    break;
                case "add_product":
                default:
                    handleAddProduct(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            doGet(request, response);
        }
    }
    private void handleAddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    }

    private void handleAddCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryName = request.getParameter("category_name");
        if (categoryName != null && !categoryName.trim().isEmpty()) {
            categoryDao.insertCategory(categoryName);
        }
        response.sendRedirect(request.getContextPath() + "/adminPage2");
    }
    private void handleDeleteList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idsStr = request.getParameter("ids");

        if (idsStr != null && !idsStr.trim().isEmpty()) {
            // Tách chuỗi thành mảng
            String[] ids = idsStr.split(",");

            productService.deleteListProducts(ids);

            // Thông báo thành công
            response.sendRedirect(request.getContextPath() + "/adminPage2?msg=deleted_list_success");
        } else {
            // Thông báo lỗi nếu danh sách rỗng
            response.sendRedirect(request.getContextPath() + "/adminPage2?error=no_selection");
        }
    }

    private void handleDeleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            categoryService.deleteCategory(id); // Gọi service để xóa
            response.sendRedirect(request.getContextPath() + "/adminPage2?msg=deleted_category");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/adminPage2?error=invalid_id");
        }
    }
    private void handleEditProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. Lấy dữ liệu từ form
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            int stock = Integer.parseInt(request.getParameter("stock"));
            int categoryId = Integer.parseInt(request.getParameter("category_id"));
            int weight = Integer.parseInt(request.getParameter("weight"));
            String state = request.getParameter("state"); // Lấy trạng thái Active/Deleted

            // 2. Tạo đối tượng Product mới để update
            Product p = new Product();
            p.setId(id);
            p.setName(name);
            p.setDescription(description);
            p.setPrice(price);
            p.setStock(stock);
            p.setCategory_id(categoryId);
            p.setWeight_grams(weight);
            p.setState(state);

            // 3. Gọi Service
            boolean isSuccess = productService.updateProduct(p);

            // 4. Phản hồi
            if (isSuccess) {
                response.sendRedirect(request.getContextPath() + "/adminPage2?msg=update_success");
            } else {
                response.sendRedirect(request.getContextPath() + "/adminPage2?error=update_failed");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/adminPage2?error=invalid_data");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/adminPage2?error=system_error");
        }
    }
}
