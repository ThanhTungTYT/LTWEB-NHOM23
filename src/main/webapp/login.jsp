<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
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
                <span id="num-cart-label">0</span>
            </div>
            <a href="login.html"><i class="fa-solid fa-user"></i></a>
        </div>
    </div>
    <div class="bottom">
        <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a>
        <a href="${pageContext.request.contextPath}/catalog">Sản phẩm</a>
        <a href="${pageContext.request.contextPath}/contact">Liên hệ</a>
        <a href="aboutUs.html">Giới thiệu</a>
    </div>
</header>
<div class="container">
    <h2 id="Login">Đăng nhập</h2>
    <form id="f-login" action="login" method="post">
        <label class="lbu" for="username">Tên đăng nhập:</label>
        <input type="text" id="username" name="email" placeholder="Tên đăng nhập hoặc email" required><br><br>
        <label class="lbpw" for="password">Mật khẩu:</label>
        <input type="password" id="password" name="password" placeholder="Mật khẩu" required><br><br>
        <div class="forgot-password">
            <p>Quên mật khẩu? <a href="forgotpassword.html">Click vào đây</a></p>
        </div>
        <p>${error}</p>
        <button type="submit" id="b-login">Đăng nhập</button>
    </form>
    <div class="register-link">
        <p>Bạn chưa có tài khoản? <a href="${pageContext.request.contextPath}/register.jsp">Đăng ký ở đây</a></p></div>
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
<button class="slide-top" id="slide-top"><i class="fas fa-angle-up"></i></button>
<script src="${pageContext.request.contextPath}/assets/js/script.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/login.js"></script>
</body>
</html>
