<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%--
  Created by IntelliJ IDEA.
  User: TDat
  Date: 14/12/2025
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Aroma Café Cà phê rang số 1 Việt Nam</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/account.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.0/css/all.min.css">
</head>
<body>
<header>
    <div class="top">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/assets/img/logo.png" onclick="location.href='${pageContext.request.contextPath}/'" width="300px" height="100px">
        </div>
        <div class="search-bar">
            <input type="text" id="search-input" placeholder="Tìm kiếm...">
            <button id="search-button"><i class="fas fa-search"></i></button>
        </div>
        <div class="mini-menu">
            <div class="cart">
                <a href="cart.html"><i class="fas fa-shopping-cart"></i></a>
                <span id="num-cart-label">3</span>
            </div>
            <a href="account.html"><i class="fa-solid fa-user"></i></a>
        </div>
    </div>
    <div class="bottom">
        <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a>
        <a href="${pageContext.request.contextPath}/catalog">Sản phẩm</a>
        <a href="${pageContext.request.contextPath}/contact">Liên hệ</a>
        <a href="${pageContext.request.contextPath}/about">Giới thiệu</a>
    </div>
</header>
<div class="dashboard-container">

    <div class="sidebar">
        <nav>
            <ul>
                <li><a href="info" class="sidebar-link active">Thông tin cá nhân</a></li>
                <li><a href="history-order.html" class="sidebar-link">Lịch sử mua hàng</a></li>
                <li><a href="change-password.html" class="sidebar-link">Đổi mật khẩu</a></li>
                <li><a href="${pageContext.request.contextPath}/logout" class="logout-link">Đăng xuất</a></li>
            </ul>
        </nav>
    </div>

    <main id="content-area" class="content-area"></main>
</div>
<footer class="footer">
    <div class="footer-top">
        <div class="foot-content left">
            <h3>Aroma Café</h3>
            <p>Địa chỉ: xxx, xxx, xxx.</p>
            <p>Điện thoại: xxx-xxx-xxxx.</p>
            <p>Email: example@gmail.com</p>
        </div>

        <div class="foot-content footer-links">
            <h3>Quy định & Chính sách</h3>
            <ul>
                <li><a href="shippingPolicies.html">Chính sách vận chuyển</a></li>
                <li><a href="warrantyPolicies.html">Chính sách bảo hành, đổi trả</a></li>
                <li><a href="termOfUse.html">Điều khoản sử dụng</a></li>
            </ul>
        </div>

        <div class="foot-content right">
            <h3>Kết nối với chúng tôi</h3>
            <div class="social">
                <a href="#"><i class="fab fa-facebook-f"></i></a>
                <a href="#"><i class="fab fa-twitter"></i></a>
                <a href="#"><i class="fab fa-instagram"></i></a>
                <a href="#"><i class="fab fa-linkedin-in"></i></a>
            </div>
        </div>
    </div>
    <div class="footer-bottom">
        <p>&copy; 2024 Aroma Café. All rights reserved.</p>
    </div>
</footer>
</body>
<button class="slide-top" id="slide-top"><i class="fas fa-angle-up"></i></button>
<script src="${pageContext.request.contextPath}/assets/js/account.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/script.js"></script>
</html>