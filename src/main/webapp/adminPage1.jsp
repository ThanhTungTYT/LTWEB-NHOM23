<%--
  Created by IntelliJ IDEA.
  User: TDat
  Date: 27/12/2025
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Trang Quản Trị Aroma Café</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
</head>
<body>
<div class="left-menu" id="left-menu">
    <div class="logo">
        <img src="${pageContext.request.contextPath}/assets/img/logo.png" onclick="location.href='${pageContext.request.contextPath}/'" width="300px" height="100px">
    </div>
    <div class="menu">
        <a href="${pageContext.request.contextPath}/admin/dashboard" class="menu-item active">Tổng quan</a>
        <a href="${pageContext.request.contextPath}/admin/products" class="menu-item">Quản lí sản phẩm</a>
        <a href="${pageContext.request.contextPath}/admin/orders" class="menu-item">Quản lí đơn hàng</a>
        <a href="${pageContext.request.contextPath}/admin/users" class="menu-item">Quản lí tài khoản</a>
        <a href="${pageContext.request.contextPath}/admin/reviews" class="menu-item">Quản lí đánh giá</a>
        <a href="${pageContext.request.contextPath}/admin/banner" class="menu-item">Quản lí banner</a>
        <a href="${pageContext.request.contextPath}/admin/promotion" class="menu-item">Quản lí mã giảm giá</a>
        <a href="${pageContext.request.contextPath}/admin/contact" class="menu-item">Chăm sóc khách hàng</a>
        <a href="#" class="menu-item" onclick="location.href='${pageContext.request.contextPath}/logout'">Đăng xuất</a>
    </div>
    <div class="footer">
        <p>2024 Aroma Café. All rights reserved.</p>
    </div>
</div>
<div class="right-content" id="right-content">
    <div class="title">
        <button class="slider-menu" id="slider-menu"><i class="fa-solid fa-bars"></i></button>
        <p>TỔNG QUAN</p>
    </div>
    <div class="filter-section">

        <form method="get" action="${pageContext.request.contextPath}/adminPage1">
            <select name="filter" onchange="this.form.submit()">
                <option value="today" ${filter == 'today' ? 'selected' : ''}>Hôm nay</option>
                <option value="week" ${filter == 'week' ? 'selected' : ''}>7 ngày</option>
                <option value="month" ${filter == 'month' ? 'selected' : ''}>30 ngày</option>
                <option value="quarter" ${filter == 'quarter' ? 'selected' : ''}>1 quý</option>
            </select>
        </form>

        <button><a href="${pageContext.request.contextPath}/adminPage1" class="reset-link">Đặt lại</a></button>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/adminPage1" class="main-menu-date">
        <div class="start">
            <label>Start date</label>
            <input type="date" name="startDate" value="${startDate}">
        </div>
        <div class="end">
            <label>End date</label>
            <input type="date" name="endDate" value="${endDate}">
        </div>
        <button>Xác nhận</button>
    </form>

        <div class="kpi-grid">

            <div class="kpi-card">
                <div class="card-icon blue"><i class="fa-solid fa-chart-line"></i></div>
                <div class="card-info">
                    <h3>
                    <fmt:formatNumber value="${totalRevenue}" type="number" groupingUsed="true"/> VND
                   </h3>
                    <span>Tổng doanh thu</span>
                </div>
            </div>

            <div class="kpi-card">
                <div class="card-icon green"><i class="fa-solid fa-box"></i></div>
                <div class="card-info">
                    <h3>${totalOrders}</h3>
                    <span>Đơn hàng</span>
                </div>
            </div>

            <div class="kpi-card">
                <div class="card-icon orange"><i class="fa-solid fa-clock"></i></div>
                <div class="card-info">
                    <h3>${pendingOrders}</h3>
                    <span>Đơn chờ xử lý</span>
                </div>
            </div>

            <div class="kpi-card">
                <div class="card-icon red"><i class="fa-solid fa-user-plus"></i></div>
                <div class="card-info">
                    <h3>${newCustomers}</h3>
                    <span>Khách mới</span>
                </div>
            </div>

        </div>
        <div class="charts-grid">
            <div class="chart-card large">
                <h3>Tổng doanh thu</h3>
                <p class="sum-total">
                    <span><i class="fa-solid fa-chart-line"></i></span>
                    <fmt:formatNumber value="${totalRevenue}" type="number" groupingUsed="true"/> VND
                </p>
            </div>
            <div class="chart-card">
                <h3 >Top sản phẩm bán chạy</h3>
                <c:forEach items="${topProducts}" var="row">
                    <p>
                            ${row.product.name} :
                            ${row.totalSold} sản phẩm
                    </p>
                </c:forEach>

                <c:if test="${empty topProducts}">
                    <p>Không có dữ liệu</p>
                </c:if>
            </div>
        </div>
        <div class="table-card">
            <h3>Đơn hàng gần đây</h3>

            <table>
                <thead>
                <tr>
                    <th>Mã ĐH</th>
                    <th>Người nhận</th>
                    <th>Ngày đặt</th>
                    <th>Trạng thái</th>
                    <th>Tổng tiền</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${orders}" var="o">
                    <tr>
                        <td>#${o.id}</td>
                        <td>${o.receiverName}</td>
                        <td>${o.createdAt}</td>
                        <td>
                        <span class="status
                            ${o.status == 'Đang xử lý' ? 'pending' :
                              o.status == 'Đã giao' ? 'completed' : 'cancelled'}">
                                ${o.status}
                        </span>
                        </td>
                        <td><fmt:formatNumber value="${o.finalAmount} " type="number" groupingUsed="true"/> VND</td>
                    </tr>
                </c:forEach>

                <c:if test="${empty orders}">
                    <tr>
                        <td colspan="5" style="text-align:center">Không có đơn hàng</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
</div>
<button class="slide-top" id="slide-top"><i class="fas fa-angle-up"></i></button>
<script src="${pageContext.request.contextPath}/assets/js/admin.js"></script>

</body>
</html>
