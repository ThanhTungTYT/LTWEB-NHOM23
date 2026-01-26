package com.example.ltwebnhom23.controller.adminPage1;

import com.example.ltwebnhom23.model.Order;
import com.example.ltwebnhom23.services.AdminPage1Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "AdminPage1Servlet", urlPatterns = {"/adminPage1"})
public class AdminPage1Servlet extends HttpServlet {

    private AdminPage1Service service = new AdminPage1Service();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String filter = request.getParameter("filter");

        // nếu chưa chọn filter → mặc định today
        if (filter == null || filter.isEmpty()) {
            filter = "today";
        }

        loadByFilter(request, filter);

        request.setAttribute("filter", filter);
        request.getRequestDispatcher("/adminPage1.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        if (startDate != null && endDate != null) {
            Timestamp start = Timestamp.valueOf(startDate + " 00:00:00");
            Timestamp end = Timestamp.valueOf(endDate + " 23:59:59");

            loadByDate(request, start, end);

            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
        }

        request.getRequestDispatcher("/adminPage1.jsp").forward(request, response);
    }

    /* ================= PRIVATE METHODS ================= */

    private void loadByFilter(HttpServletRequest request, String filter) {

        request.setAttribute("totalRevenue", service.getTotalRevenue(filter));
        request.setAttribute("totalOrders", service.getTotalOrders(filter));
        request.setAttribute("pendingOrders", service.getPendingOrders(filter));
        request.setAttribute("newCustomers", service.getNewCustomers(filter));
        request.setAttribute("topProducts", service.getTopProducts(filter));

        List<Order> orders = service.getOrders(filter);
        request.setAttribute("orders", orders);
    }

    private void loadByDate(HttpServletRequest request, Timestamp start, Timestamp end) {

        request.setAttribute("totalRevenue", service.getTotalRevenue(start, end));
        request.setAttribute("totalOrders", service.getTotalOrders(start, end));
        request.setAttribute("pendingOrders", service.getPendingOrders(start, end));
        request.setAttribute("newCustomers", service.getNewCustomers(start, end));
        request.setAttribute("topProducts", service.getTopProducts(start, end));

        List<Order> orders = service.getOrders(start, end);
        request.setAttribute("orders", orders);
    }
}
