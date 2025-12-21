<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 13/12/2025
  Time: 9:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/catalog.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body>
<header>
    <div class="top">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/assets/img/logo.png" onclick="location.href='${pageContext.request.contextPath}/index.jsp'" width="300px" height="100px">
        </div>e
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
<div class="catalog">
    <div class="catalog-list" id="catalog-list">
        <p class="head-catalog">Danh mục sản phẩm</p>
        <ul class="list-catalog" id="list-catalog">
            <li> <a href="catalog?cid=0" class="catalog-item ${param.cid == null || param.cid == '0' ? 'active' : ''}">Tất cả</a> </li>
            <c:forEach items="${listCategories}" var="c">
                <li> <a href="catalog?cid=${c.category_id}" class="catalog-item ${param.cid == c.category_id ? 'active' : ''}">${c.category_name}</a> </li>
            </c:forEach>
        </ul>
        <hr>
        <div class="filter">
            <select>
                <option>-Chọn lựa-</option>
                <option>Giá cao đến thấp</option>
                <option>Giá thấp đến cao</option>
                <option>Lượt mua nhiều nhất</option>
                <option>Lượt đánh giá</option>
            </select>
        </div>
    </div>
    <div class="product-area" id="product-area">
        <div class="product-list" id="product-list">
            <c:forEach items="${listProducts}" var="p">
                <a href="product?pid=${p.product_id}" class="product">
                    <img src="${p.image_url}">
                    <p>${p.product_name}</p>
                    <span>Giá: ${p.price}</span>
                    <label>Loại: ${p.category_name}</label>
                </a>
            </c:forEach>
        </div>
        <div class="product-page" id="pagination">
            <button id="prev-page"><i class="fas fa-chevron-left"></i></button>
            <input type="number" id="page-input" min="1" value="1"/>
            <button id="next-page"><i class="fas fa-chevron-right"></i></button>
        </div>
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
</body>
</html>