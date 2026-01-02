<%--
  Created by IntelliJ IDEA.
  User: TDat
  Date: 27/12/2025
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quản lí sản phẩm</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">

</head>
<body>
<div class="left-menu" id="left-menu">
    <div class="logo">
        <img src="${pageContext.request.contextPath}/assets/img/logo.png" onclick="location.href='${pageContext.request.contextPath}/index.jsp'" width="300px" height="100px">
    </div>
    <div class="menu">
        <a href="${pageContext.request.contextPath}/adminPage1.jsp" class="menu-item ">Tổng quan</a>
        <a href="${pageContext.request.contextPath}/adminPage2" class="menu-item active">Quản lí sản phẩm</a>
        <a href="${pageContext.request.contextPath}/adminPage3.jsp" class="menu-item">Quản lí đơn hàng</a>
        <a href="${pageContext.request.contextPath}/adminpage4.jsp" class="menu-item">Quản lí tài khoản</a>
        <a href="${pageContext.request.contextPath}/adminPage6.jsp" class="menu-item">Quản lí đánh giá</a>
        <a href="${pageContext.request.contextPath}/adminPage7.jsp" class="menu-item">Quản lí banner</a>
        <a href="${pageContext.request.contextPath}/adminPage8.jsp" class="menu-item">Quản lí mã giảm giá</a>
        <a href="${pageContext.request.contextPath}/adminPage5" class="menu-item">Chăm sóc khách hàng</a>
        <a href="#" class="menu-item" onclick="location.href='index.html'">Đăng xuất</a>
    </div>
    <div class="footer">
        <p>2024 Aroma Café. All rights reserved.</p>
    </div>
</div>
<div class="right-content" id="right-content">
    <div class="title">
        <button class="slider-menu" id="slider-menu"><i class="fa-solid fa-bars"></i></button>
        <p>QUẢN LÍ SẢN PHẨM</p>
    </div>
    <form class="search-bar">
        <input type="text" placeholder="Tìm kiếm sản phẩm" required>
        <button><i class="fas fa-search"></i></button>
    </form>
    <div class="main-menu">
        <select>
            <option>-Chọn dòng sản phẩm-</option>
            <option>Cà phê hữu cơ</option>
            <option>Cà phê rang nguyên hạt</option>
            <option>Cà phê xay nguyên chất</option>
            <option>Các sản phẩm đặc biệt khác</option>
        </select>
        <button type="button" onclick="addCat()" id="add-cat-btn">
            + Thêm loại sản phẩm
        </button>
        <button id="add">+ Thêm sản phẩm</button>
    </div>
    <div class="cat-list">
        <h3>DANH SÁCH LOẠI SẢN PHẨM</h3>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Tên loại sản phẩm</th>
                <th>Trạng thái</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${categories}" var="c">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.name}</td>
                    <td>
                        <span>${c.state}</span>
                    </td>
                    <td>
                        <button type="button"
                                onclick="deleteCategory(${c.id})"
                                title="Xóa loại này">
                            <i class="fa-solid fa-trash"></i>
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="list-product">
        <h3>DANH SÁCH SẢN PHẨM</h3>
        <table>
            <thead>
            <tr>
                <th></th>
                <th>ID</th>
                <th>Tên sản phẩm</th>
                <th>Loại sản phẩm</th>
                <th>Khối lượng</th>
                <th>Giá (VND)</th>
                <th>Số lượng còn</th>
                <th>Số lượng bán</th>
                <th>Trạng thái</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${products}" var="p">
                <tr>
                    <td><input type="checkbox" name="productIds" value="${p.id}"></td>

                    <td>#${p.id}</td>

                    <td style="font-weight: bold; text-align: left;">${p.name}</td>

                    <td>${p.category_name != null ? p.category_name : 'Khác'}</td>

                    <td>${p.weight_grams}</td>

                    <td style="color: #d32f2f; font-weight: bold;">
                        <fmt:formatNumber value="${p.price}" type="number" maxFractionDigits="0"/> đ
                    </td>
                    <td>${p.stock}</td>
                    <td>${p.sold}</td>
                    <td>${p.state}</td>
                    <td>
                        <button class="remake"
                                type="button"
                                data-id="${p.id}"
                                data-name="${p.name}"
                                data-category="${p.category_id}"
                                data-weight="${p.weight_grams}"
                                data-price="${p.price}"
                                data-stock="${p.stock}"
                                data-desc="${p.description}"
                                onclick="openEditModal(this)">
                            <i class="fa-solid fa-pen"></i>
                        </button>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty products}">
                <tr>
                    <td colspan="9" style="text-align:center; padding: 20px;">
                        Chưa có sản phẩm nào.
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
        <button>Xóa sản phẩm</button>
    </div>
</div>
<div class="form-add" id="form-add" style="display: none">
    <div class="form-title">
        <p>THÊM SẢN PHẨM</p>
        <button id="take-off" type="button" >X</button>
    </div>
    <form class="main-form" action="${pageContext.request.contextPath}/adminPage2" method="post">
        <input type="hidden" name="action" value="add_product">
        <div class="p name-p">
            <label>Tên sản phẩm</label>
            <input type="text" name="name" placeholder="Tên sản phẩm" required>
        </div>
        <div class="type-p">
            <label>Loại sản phẩm</label>
            <select name="category_id">
                <option value="">-- Chọn loại --</option>
                <c:forEach items="${categories}" var="cat">
                    <option value="${cat.id}">${cat.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="price-p">
            <label>Giá sản phẩm</label>
            <input type="number" name="price" placeholder="Giá sản phẩm" required>
        </div>
        <div class="p name-p">
            <label>Số lượng</label>
            <input type="number" name="stock" placeholder="Số lượng">
        </div>
        <div class="weight-p">
            <label>Khối lượng</label>
            <input type="number" name="weight" placeholder="Khối lượng" required>
        </div>
        <div class="img-p">
            <label>Ảnh</label>
            <input type="text" id="urlInput" placeholder="Url link">
            <button type="button" onclick="addImageUrl()">Thêm</button>
            <span id="countImg">0</span>
            <div id="hidden-area"></div>
        </div>
        <div class="des-p">
            <label>Mô tả</label>
            <textarea name="description" placeholder="Mô tả"></textarea>
        </div>
        <button class="submit" type="submit">Thêm</button>
    </form>
</div>
<div class="form-add" id="form-remake" style="display: none">
    <div class="form-title">
        <p>SỬA SẢN PHẨM</p>
        <button id="close-remake" type="button" >X</button>
    </div>
    <form class="main-form">
        <div class="p id-p">
            <label>ID</label>
            <p>P00#</p>
        </div>
        <div class="p name-p">
            <label>Tên sản phẩm</label>
            <input type="text" placeholder="Tên sản phẩm">
        </div>
        <div class="type-p">
            <label>Loại sản phẩm</label>
            <select name="category_id">
                <option value="">-- Chọn loại --</option>
                <c:forEach items="${categories}" var="cat">
                    <option value="${cat.id}">${cat.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="price-p">
            <label>Giá sản phẩm</label>
            <input type="text" placeholder="Giá sản phẩm">
        </div>
        <div class="weight-p">
            <label>Khối lượng</label>
            <input type="text" placeholder="Khối lượng" required>
        </div>
        <div class="p name-p">
            <label>Số lượng</label>
            <input type="text" placeholder="Số lượng">
        </div>
        <div class="des-p">
            <label>Mô tả</label>
            <textarea placeholder="Mô tả"></textarea>
        </div>
        <button class="submit" type="submit">Thêm</button>
    </form>
</div>
<div id="form-add-cat" class="form-add">

    <div class="form-title">
        <p >THÊM LOẠI SẢN PHẨM</p>
        <button type="button" onclick="dongFormThemLoai()">X</button>
    </div>

    <form class="main-form" action="${pageContext.request.contextPath}/adminPage2" method="post">
        <input type="hidden" name="action" value="add_category">
        <div class="p name-p" >
            <label>Tên loại sản phẩm</label>
            <input type="text" name="category_name" required placeholder="Nhập tên loại..." >
        </div>
        <button class="submit" type="submit">Thêm</button>
    </form>
</div>
<form id="form-delete-cat" action="${pageContext.request.contextPath}/adminPage2" method="post" style="display: none;">
    <input type="hidden" name="action" value="delete_category">
    <input type="hidden" name="id" id="input-cat-id">
</form>
<button class="slide-top" id="slide-top"><i class="fas fa-angle-up"></i></button>
<script src="${pageContext.request.contextPath}/assets/js/admin.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/adminPage2.js"></script>
</body>
</html>
