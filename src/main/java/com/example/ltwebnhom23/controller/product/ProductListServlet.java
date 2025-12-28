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
        // 1. Load danh sách danh mục (để hiển thị bên trái hoặc menu lọc)
        List<Category> listCategories = catalogService.getAllCategories();
        request.setAttribute("listCategories", listCategories);

        // 2. Lấy tham số từ URL
        String cidStr = request.getParameter("cid");
        String sort = request.getParameter("sort");
        String pageStr = request.getParameter("page"); // Thêm lấy tham số page

        // 3. Xử lý cid (Category ID)
        int cid = 0;
        if (cidStr != null && !cidStr.isEmpty()) {
            try {
                cid = Integer.parseInt(cidStr);
            } catch (NumberFormatException e) {
                cid = 0; // Nếu lỗi format thì mặc định là 0 (Tất cả)
            }
        }

        // 4. Xử lý page (Trang hiện tại)
        int page = 1;
        if (pageStr != null && !pageStr.isEmpty()) {
            try {
                page = Integer.parseInt(pageStr);
                if (page < 1) page = 1; // Đảm bảo không có trang âm
            } catch (NumberFormatException e) {
                page = 1; // Nếu lỗi format thì về trang 1
            }
        }

        // 5. Gọi Service
        // - Tính tổng số trang (để biết nút Next có disable hay không)
        int totalPages = productService.getTotalPages(cid);

        // - Lấy danh sách sản phẩm theo trang (Service sẽ tự tính offset)
        List<Product> listProducts = productService.getProductsForCatalog(cid, sort, page);

        // 6. Set Attribute để gửi ra JSP
        request.setAttribute("listProducts", listProducts);

        // Gửi lại các tham số lọc để giữ trạng thái UI
        request.setAttribute("currentCid", cid);
        request.setAttribute("currentSort", sort);

        // Gửi tham số phân trang
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("catalog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}