<%--
  Created by IntelliJ IDEA.
  User: MyPC
  Date: 21/01/2026
  Time: 12:51 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Xác thực OTP</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/register.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">

    <style>
        .otp-container {
            max-width: 500px;
            margin: 50px auto;
            background: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        .otp-input {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            text-align: center;
            letter-spacing: 8px;
            font-size: 24px;
            font-weight: bold;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .otp-message {
            text-align: center;
            font-size: 14px;
            color: #666;
            margin-bottom: 20px;
        }

        .alert-error {
            color: red;
            text-align: center;
            margin-bottom: 15px;
            font-weight: bold;
        }

        .alert-success {
            color: green;
            text-align: center;
            margin-bottom: 15px;
            font-weight: bold;
        }

        .resend-link {
            text-align: center;
            color: #c76739;
            text-decoration: none;
            font-size: 14px;
            margin-top: 10px;
            display: inline-block;
            cursor: pointer;
        }

        .resend-link.disabled {
            color: #999;
            pointer-events: none;
            cursor: default;
        }

        .back-link {
            text-align: center;
            color: #666;
            font-size: 13px;
            margin-top: 15px;
            display: block;
            text-decoration: none;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
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
            <input type="text" id="search-input" placeholder="Tìm kiếm...">
            <button id="search-button"><i class="fas fa-search"></i></button>
        </div>
        <div class="mini-menu">
            <div class="cart">
                <a href="${pageContext.request.contextPath}/cart.jsp"><i class="fas fa-shopping-cart"></i></a>
                <span id="num-cart-label">${sessionScope.cart.totalQuantity}</span>
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

<div class="otp-container">
    <h2 style="text-align: center; margin-bottom: 15px; color: #333;">XÁC THỰC TÀI KHOẢN</h2>
    <p class="otp-message">
        Một mã xác thực 6 số đã được gửi tới email của bạn.<br>
        Mã có hiệu lực trong 10 phút.
    </p>

    <c:if test="${not empty error}">
        <p class="alert-error">${error}</p>
    </c:if>
    <c:if test="${not empty message}">
        <p class="alert-success">${message}</p>
    </c:if>

    <form action="verify-otp" method="post">

        <label class="lbn" for="otp" style="font-weight: bold; display: block; margin-bottom: 5px;">Nhập Mã OTP:</label>

        <input type="text" id="otp" name="otp"
               class="otp-input"
               placeholder="______"
               maxlength="6"
               required
               oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');">
        <div class="bt_regis" style="display: flex; flex-direction: column; gap: 10px;">
            <button type="submit" id="register-btn">Xác nhận</button>
        </div>
    </form>

    <div style="text-align: center;">
        <a href="resend-otp" id="resend-link" class="resend-link disabled">
            Gửi lại mã (120s)
        </a>
    </div>

    <a href="${pageContext.request.contextPath}/register.jsp" class="back-link">
        <i class="fas fa-arrow-left"></i> Quay lại trang đăng ký
    </a>
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
