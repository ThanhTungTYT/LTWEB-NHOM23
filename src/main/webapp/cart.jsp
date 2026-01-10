<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Giỏ hàng| Aroma Café</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/cart.css">
</head>
<body>
<header>
  <div class="top">
    <div class="logo">
      <img src="${pageContext.request.contextPath}/assets/img/logo.png"
           onclick="location.href='index.jsp'"
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
<div class="cart-container">
  <h2>Giỏ hàng</h2>

  <div class="clear-all-container" style="display: flex; justify-content: space-between;">
    <a href="#" class="select-all-cart">Chọn tất cả</a>
    <a href="${pageContext.request.contextPath}/remove-all" class="clear-all-cart">Xóa tất cả</a>
  </div>

  <div id="cart-list">
      <c:forEach items="${sessionScope.cart.list}" var="item">
        <div class="cart-item" data-price="${item.price}">
          <input type="hidden" name="pid" value="${item.product.id}">
          <input type="checkbox" class="product-select">
          <a href="${pageContext.request.contextPath}/remove-item?pid=${item.product.id}" class="product-remove">
              <i class="fas fa-times"></i>
          </a>
          <div class="product-thumbnail">
            <img src="${item.product.image_url}" alt="${item.product.name}"/>
          </div>
          <div class="product-details">
            <p class="product-name">${item.product.name}</p>
            <p class="product-type">Loại: ${item.product.category_name}</p>
            <p class="product-weight">
              Khối lượng: <span>${item.product.weight_grams}gr</span>
            </p>
          </div>
          <div class="product-price">
            <fmt:formatNumber value="${item.price}" type="number"/>đ
          </div>
          <div class="product-quantity">
            <button class="btn-decrease">-</button>
            <input type="number" value="${item.quantity}" min="1"/>
            <button class="btn-increase">+</button>
          </div>
          <div class="product-subtotal">
            <fmt:formatNumber
                    value="${item.price * item.quantity}"
                    type="number"/>đ
          </div>
        </div>
      </c:forEach>
  </div>
  <div class="cart-totals">
    <h3>TỔNG CỘNG</h3>
    <span id="cart-total">
    <fmt:formatNumber value="${sessionScope.cart.total}" type="number"/>đ
    </span>
    <p>(Chỉ tính các sản phẩm được chọn)</p>
  </div>

  <a href="payment.html" type="button" class="checkout-button"> Thanh Toán </a>

  <div class="payment-methods">
    <h4>Phương thức thanh toán</h4>
    <i class="fa-solid fa-boxes-stacked"></i>
    <span>Thanh Toán Khi Nhận Hàng</span>
    <i class="fa-brands fa-cc-visa"></i>
    <span>Chuyển Khoản Qua Ngân Hàng</span>
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

<script src="${pageContext.request.contextPath}/assets/js/cart.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/script.js"></script>
</body>
</html>
