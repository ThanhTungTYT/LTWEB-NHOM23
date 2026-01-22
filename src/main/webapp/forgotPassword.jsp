<%--
  Created by IntelliJ IDEA.
  User: MyPC
  Date: 22/01/2026
  Time: 3:07 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <title>Lấy lại mật khẩu | Aroma Café</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/forgotpassword.css">
</head>
<body>

<header>
    <div class="top">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/assets/img/logo.png"
                 onclick="location.href='${pageContext.request.contextPath}/index.jsp'"
                 width="300px" height="100px" alt="Logo">
        </div>
        <div class="search-bar">
            <form action="search" method="get" style="display: flex; width: 100%;">
                <input type="text" id="search-input" name="q" placeholder="Tìm kiếm...">
                <button id="search-button" type="submit"><i class="fas fa-search"></i></button>
            </form>
        </div>
        <div class="mini-menu">
            <div class="cart">
                <a href="${pageContext.request.contextPath}/cart.jsp"><i class="fas fa-shopping-cart"></i></a>
                <span id="num-cart-label">${sessionScope.cart.totalQuantity != null ? sessionScope.cart.totalQuantity : 0}</span>
            </div>
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <a href="${pageContext.request.contextPath}/account">
                        <i class="fas fa-user"></i>
                        <span style="font-size: 14px; margin-left: 5px">${sessionScope.user.full_name}</span>
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login.jsp">
                        <i class="fas fa-user"></i>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="bottom">
        <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a>
        <a href="${pageContext.request.contextPath}/catalog">Sản phẩm</a>
        <a href="${pageContext.request.contextPath}/contact">Liên hệ</a>
        <a href="${pageContext.request.contextPath}/about">Giới thiệu</a>
    </div>
</header>

<div class="container">
    <h2 id="forgot-password">Lấy lại mật khẩu</h2>

    <form action="forgot-password" method="post">

        <label class="lbu" for="username">Email:</label>
        <input type="email" id="username" name="email" placeholder="Nhập email của bạn" required>

        <c:if test="${not empty error}">
            <div style="color: #721c24; background-color: #f8d7da; padding: 10px; margin-bottom: 15px; border-radius: 4px;">
                <i class="fas fa-exclamation-triangle"></i> ${error}
            </div>
        </c:if>
        <c:if test="${not empty message}">
            <div style="color: #155724; background-color: #d4edda; padding: 10px; margin-bottom: 15px; border-radius: 4px;">
                <i class="fas fa-check-circle"></i> ${message}
            </div>
        </c:if>

        <br>
        <div class="bt_forgot">
            <button type="button" id="b-return" onclick="location.href='${pageContext.request.contextPath}/login.jsp'">Quay lại</button>
            <button type="submit" id="b-forgot">Lấy lại mật khẩu</button>
        </div>
    </form>
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
                <li><a href="${pageContext.request.contextPath}/policy?type=shipping">Chính sách vận chuyển</a></li>
                <li><a href="${pageContext.request.contextPath}/policy?type=warranty">Chính sách bảo hành, đổi trả</a></li>
                <li><a href="${pageContext.request.contextPath}/policy?type=terms">Điều khoản sử dụng</a></li>
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

<button class="slide-top" id="slide-top"><i class="fas fa-angle-up"></i></button>

<script src="${pageContext.request.contextPath}/assets/js/script.js"></script>
</body>
</html>
