<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<style>
    .info-container {
        background: #fff;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        max-width: 800px;
        margin: 0 auto;
    }
    .info-container h2 {
        border-bottom: 2px solid #eee;
        padding-bottom: 15px;
        margin-bottom: 25px;
        color: #333;
    }
    .info-form label {
        font-weight: 600;
        color: #555;
        margin-bottom: 5px;
        display: block;
    }
    .info-form input {
        width: 100%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ddd;
        border-radius: 5px;
        font-size: 14px;
    }
    .info-form input:focus {
        border-color: #d2691e;
        outline: none;
    }
    .btn-update {
        background-color: #d2691e;
        color: white;
        padding: 12px 25px;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        transition: background 0.3s;
    }
    .btn-update:hover {
        background-color: #a0522d;
    }
</style>

<div class="info-container">
    <h2>Thông tin cá nhân</h2>
    <form action="update-info" method="post" class="info-form">
        <input type="hidden" name="id" value="${user.user_id}">

        <label>Họ và tên:</label>
        <input type="text" name="fullname" value="${user.full_name}" required>
        <label>Email:</label>
        <input type="email" name="email" value="${user.email}" readonly style="background-color: #e9ecef; cursor: not-allowed;">

        <label>Số điện thoại:</label>
        <input type="text" name="phone" value="${user.phone}" placeholder="Nhập số điện thoại">

        <label>Tỉnh/TP:</label>
        <input type="text" name="city" placeholder=" Tỉnh/TP"value="${addr != null ? addr.province : ''}">

        <label>Quận/Huyện, Xã/Phường:</label>
        <input type="text" name="district" placeholder=" Quận/Huyện"value="${addr != null ? addr.ward : ''}">

        <label>Đường, Số nhà:</label>
        <input type="text" name="address" placeholder="số nhà, tên đường"value="${addr != null ? addr.address : ''}">
        <input type="hidden" name="addressId" value="${addr != null ? addr.id_address : 0}">

        <button type="submit" class="btn-update">Cập nhật thông tin</button>
    </form>
</div>