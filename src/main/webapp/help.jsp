<%--
  Created by IntelliJ IDEA.
  User: MyPC
  Date: 19/12/2025
  Time: 8:46 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Aroma Café Cà phê rang số 1 Việt Nam</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/help.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.0/css/all.min.css">
</head>
<body>

<header>
    <div class="top">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/assets/img/logo.png" onclick="location.href='${pageContext.request.contextPath}/index.jsp'" width="300px" height="100px">
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
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <a href="${pageContext.request.contextPath}/account.jsp">
                        <i class="fas fa-user"></i>
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
    <h1>Aroma Café - Cà phê rang số 1 Việt Nam</h1>

    <div class="address">
        <div><i class="fa-solid fa-location-dot"></i> Địa chỉ: xxx , xxx, xxx.</div>
        <div><i class="fa-solid fa-phone"></i> Điện thoại: xxx-xxx-xxxx.</div>
        <div><i class="fa-solid fa-envelope"></i> Email: aromacafe@gmail.com</div>
    </div>

    <div class="main-content">
        <h2>Liên hệ với chúng tôi</h2>

        <!-- Thông báo gửi thành công -->
        <c:if test="${not empty success}">
            <p style="color: green; margin-bottom: 15px;">
                    ${success}
            </p>
        </c:if>

        <form action="${pageContext.request.contextPath}/contact" method="post">
            <input type="text" name="name" placeholder="Họ và tên của bạn" required>
            <input type="email" name="email" placeholder="Email của bạn" required>

            <label for="message">Tin nhắn:</label>
            <textarea name="message" rows="5"
                      placeholder="Viết tin nhắn của bạn ở đây..." required></textarea>

            <button type="submit">Liên hệ ngay</button>
        </form>
    </div>
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


