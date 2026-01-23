<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh toán - Aroma Café</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/payment.css">
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

<form action="payment" method="post" id="checkout-form">

    <div class="checkout-container">
        <div class="back-link">
            <a href="cart.jsp"><i class="fas fa-arrow-left"></i> Quay lại giỏ hàng</a>
        </div>

        <%-- Hiển thị thông báo lỗi nếu Servlet trả về --%>
        <c:if test="${not empty requestScope.error}">
            <div style="background-color: #f8d7da; color: #721c24; padding: 10px; margin-bottom: 15px; border-radius: 5px; text-align: center;">
                <i class="fas fa-exclamation-triangle"></i> ${requestScope.error}
            </div>
        </c:if>

        <div class="checkout-left">
            <section class="account-box">
                <h3>Tài khoản</h3>
                <div class="account-info">
                    <div class="avatar-icon" id="account-btn">
                        <i class="fas fa-user"></i>
                    </div>
                    <div>
                        <p class="name">${sessionScope.user.full_name}</p>
                        <p class="email">${sessionScope.user.email}</p>
                    </div>
                </div>
            </section>

            <section class="shipping-box">
                <h3>Thông tin giao hàng</h3>
                <div class="shipping-form">
                    <input type="text" id="fullname" name="fullname" placeholder="Họ và tên"
                           value="${sessionScope.user.full_name}" required>
                    <small class="error"></small>

                    <input type="text" id="phone" name="phone" placeholder="Số điện thoại"
                           value="${sessionScope.user.phone}" required>
                    <small class="error"></small>

                    <input type="text" id="country" name="country" placeholder="Quốc gia" value="Việt Nam" required>
                    <small class="error"></small>

                    <input type="text" id="province" name="province" placeholder="Tỉnh/TP" required>
                    <small class="error"></small>

                    <input type="text" id="ward" name="ward" placeholder="Quận/Huyện, Phường/Xã" required>
                    <small class="error"></small>

                    <input type="text" id="address" name="address" placeholder="Đường, Số nhà" required>
                    <small class="error"></small>
                </div>
            </section>

            <section class="payment-method">
                <h3>Phương thức thanh toán</h3>
                <label class="radio-item">
                    <input type="radio" name="paymentMethod" value="cod" checked> Thanh toán khi nhận hàng
                </label>
                <label class="radio-item">
                    <input type="radio" name="paymentMethod" value="bank"> Chuyển khoản ngân hàng
                </label>
            </section>

            <section class="note-box">
                <h3>Ghi chú</h3>
                <textarea name="note" placeholder="Nhập ghi chú cho đơn hàng (nếu có)"></textarea>
            </section>
        </div>

        <div class="checkout-right">
            <section class="cart-box">
                <h3>Giỏ hàng</h3>
                <div id="cart-list">
                    <c:forEach items="${sessionScope.cart.list}" var="item">
                        <%-- data-price dùng cho JS tính toán phía client --%>
                        <div class="cart-item" data-price="${item.price}">
                            <img src="${item.product.image_url}" alt="${item.product.name}" onerror="this.src='${pageContext.request.contextPath}/assets/img/default.png'">
                            <div class="cart-info">
                                <p class="product-name">${item.product.name}</p>
                                <p class="price"> Giá:
                                    <fmt:formatNumber value="${item.price}" type="currency" currencySymbol="đ"/>
                                </p>
                            </div>
                            <div class="quantity-box">
                                <p>Số lượng: ${item.quantity}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </section>

            <section class="discount-box">
                <h3>Mã khuyến mãi</h3>

                <select name="promotionId" id="promotionSelect">
                    <option value="" data-discount="0">-- Chọn mã --</option>
                    <c:forEach var="p" items="${promotions}">
                        <option value="${p.id}"
                                data-discount="${p.discountPercent}">
                                ${p.code} - Giảm ${p.discountPercent}%
                        </option>
                    </c:forEach>
                </select>
            </section>
            <section class="summary-box">
                <h3>Tóm tắt đơn hàng</h3>
                <!-- Tổng tiền hàng -->
                <div class="summary-row">
                    <span>Tổng tiền hàng</span>
                    <span id="total-price"
                          data-total="${sessionScope.cart.total}">
            <fmt:formatNumber
                    value="${sessionScope.cart.total}"
                    type="currency"
                    currencySymbol="₫"/>
        </span>
                </div>

                <!-- Phí vận chuyển -->
                <div class="summary-row">
                    <span>Phí vận chuyển</span>
                    <span id="shipping-fee" data-fee="30000">30.000₫</span>
                </div>
                <hr>
                <!-- Tổng thanh toán -->
                <div class="summary-row total">
                    <span>Tổng thanh toán</span>
                    <span id="final-total"
                          data-base="${sessionScope.cart.total}"
                          data-ship="30000">
                        <fmt:formatNumber
                                value="${sessionScope.cart.total + 30000}"
                                type="currency"
                                currencySymbol="₫"/>
                    </span>
                </div>
                <button type="submit" class="checkout-btn">
                    Đặt hàng
                </button>
            </section>
        </div>
    </div>
</form>

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

<script src="${pageContext.request.contextPath}/assets/js/payment.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/script.js"></script>

</body>
</html>