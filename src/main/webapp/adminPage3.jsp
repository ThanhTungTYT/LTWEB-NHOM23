<%--
  Created by IntelliJ IDEA.
  User: TDat
  Date: 27/12/2025
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Trang Quản Trị Aroma Café</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">
    <style>
        /* Thêm style để đảm bảo bố cục hoạt động */
        body {
            display: flex;
            flex-direction: row;
        }

        .right-content {
            width: 80%; /* Hoặc 100% nếu menu bị ẩn */
            transition: width 0.3s ease;
        }
    </style>
</head>
<body>
<div class="left-menu" id="left-menu">
    <div class="logo">
        <img src="${pageContext.request.contextPath}/assets/img/logo.png" onclick="location.href='${pageContext.request.contextPath}/index.jsp'" width="300px" height="100px">
    </div>
    <div class="menu">
        <a href="${pageContext.request.contextPath}/adminPage1.jsp" class="menu-item ">Tổng quan</a>
        <a href="${pageContext.request.contextPath}/adminPage2" class="menu-item">Quản lí sản phẩm</a>
        <a href="${pageContext.request.contextPath}/adminPage3.jsp" class="menu-item active">Quản lí đơn hàng</a>
        <a href="${pageContext.request.contextPath}/adminpage4.jsp" class="menu-item">Quản lí tài khoản</a>
        <a href="${pageContext.request.contextPath}/adminPage6.jsp" class="menu-item">Quản lí đánh giá</a>
        <a href="${pageContext.request.contextPath}/adminPage7.jsp" class="menu-item">Quản lí banner</a>
        <a href="${pageContext.request.contextPath}/adminPage8.jsp" class="menu-item">Quản lí mã giảm giá</a>
        <a href="${pageContext.request.contextPath}/adminPage5.jsp" class="menu-item">Chăm sóc khách hàng</a>
        <a href="#" class="menu-item" onclick="location.href='index.html'">Đăng xuất</a>
    </div>
    <div class="footer">
        <p>2024 Aroma Café. All rights reserved.</p>
    </div>
</div>
<div class="right-content" id="right-content">
    <div class="title">
        <button class="slider-menu" id="slider-menu"><i class="fa-solid fa-bars"></i></button>
        <p>ĐƠN HÀNG</p>
    </div>
    <div class="main-content">
        <form class="main-menu-date">
            <div class="start">
                <label>Start date</label>
                <input type="date">
            </div>
            <div class="end">
                <label>End date</label>
                <input type="date">
            </div>
            <button>Xác nhận</button>
        </form>
        <div class="list-order">
            <h3>DANH SÁCH ĐƠN HÀNG</h3>
            <table>
                <thead>
                <tr>
                    <th></th>
                    <th>Mã ĐH</th>
                    <th>Khách hàng</th>
                    <th>Ngày đặt</th>
                    <th>Trạng thái</th>
                    <th>Tổng tiền</th>
                    <th>Phương thức thanh toán</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <button class="detail"><i class="fa-solid fa-list"></i></button>
                    </td>
                    <td>#DH10542</td>
                    <td>Nguyễn Văn A</td>
                    <td>09/11/2025</td>
                    <td><span class="status pending">Chờ xử lý</span></td>
                    <td>120.000đ</td>
                    <td>Thanh toán khi nhận hàng</td>
                </tr>
                <tr>
                    <td>
                        <button class="detail"><i class="fa-solid fa-list"></i></button>
                    </td>
                    <td>#DH10541</td>
                    <td>Trần Thị B</td>
                    <td>09/11/2025</td>
                    <td><span class="status completed">Đã giao</span></td>
                    <td>150.000đ</td>
                    <td>Chuyển khoản qua ngân hàng</td>
                </tr>
                <tr>
                    <td>
                        <button class="detail"><i class="fa-solid fa-list"></i></button>
                    </td>
                    <td>#DH10540</td>
                    <td>Lê Văn C</td>
                    <td>08/11/2025</td>
                    <td><span class="status cancelled">Đã hủy</span></td>
                    <td>250.000đ</td>
                    <td>Chuyển khoản qua ngân hàng</td>
                </tr>
                <tr>
                    <td>
                        <button class="detail"><i class="fa-solid fa-list"></i></button>
                    </td>
                    <td>#DH10539</td>
                    <td>Phạm Thị D</td>
                    <td>08/11/2025</td>
                    <td><span class="status completed">Đã giao</span></td>
                    <td>55.000đ</td>
                    <td>Thanh toán khi nhận hàng</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="detail-p" id="detail-p" style="display: none">
    <button id="close">X</button>
    <div class="order">
        <h3>DANH SÁCH SẢN PHẨM</h3>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Tên sản phẩm</th>
                <th>Loại sản phẩm</th>
                <th>Giá (VND)</th>
                <th>Số lượng</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>P001</td>
                <td>Cafe Aroma 1</td>
                <td>Cà phê hữu cơ</td>
                <td>100.000</td>
                <td>1</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="info-customer">
        <h3>THÔNG TIN KHÁCH HÀNG</h3>
        <div class="info">
            <div class="info-left">
                <p>1. Họ và tên: Phan Thanh Tùng</p>
                <p>2. SĐT: 0868.xxx.xxx</p>
                <p>3. Email: example@gmail.com</p>
            </div>
            <div class="info-right">
                <p>4. Tỉnh: Gia Lai</p>
                <p>5. Xã: Hòa Hội</p>
                <p>6. Địa chỉ: Số 1xx, đường 1x, thôn Hòa Hội</p>
            </div>
        </div>
    </div>
    <div class="totalSum">
        <h3>PHÍ VẬN CHUYỂN: <span>20.000</span> VND</h3>
        <h3>TỔNG TIỀN: <span>120.000</span> VND</h3>
    </div>
    <button>Xuất hóa đơn</button>
</div>
<button class="slide-top" id="slide-top"><i class="fas fa-angle-up"></i></button>
<script src="${pageContext.request.contextPath}/assets/js/admin.js"></script>

</body>
</html>