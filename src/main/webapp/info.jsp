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

    .info-form input,
    .info-form select {
        width: 100% !important;
        max-width: 100% !important;
        box-sizing: border-box !important;
        display: block !important;
        padding: 10px 12px !important;
        margin: 0 0 15px 0 !important;
        border: 1px solid #ddd !important;
        border-radius: 5px !important;
        font-size: 14px !important;
        font-family: inherit !important;
        height: 42px !important;
    }

    .info-form input[readonly],
    .info-form select:disabled {
        background-color: #f9f9f9;
        cursor: default;
        color: #555;
        border-color: transparent;
        appearance: none;
        -webkit-appearance: none;
        -moz-appearance: none;
    }

    .info-form input:not([readonly]),
    .info-form select:not(:disabled) {
        background-color: #fff;
        border-color: #d2691e;
        appearance: auto;
        -webkit-appearance: auto;
        -moz-appearance: auto;
        cursor: pointer;
    }

    .input-fixed {
        background-color: #f9f9f9 !important;
        cursor: not-allowed !important;
    }

    .info-form input:focus,
    .info-form select:focus {
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
    <form id="userForm" action="update-info" method="post" class="info-form">
        <input type="hidden" name="id" value="${user.id}">

        <label>Họ và tên:</label>
        <input type="text" name="fullname" class="editable" value="${user.full_name}" readonly required>
        <label>Email:</label>
        <input type="email" name="email" class="input-fixed" value="${user.email}" readonly>

        <label>Số điện thoại:</label>
        <input type="text" name="phone" class="editable" value="${user.phone}" readonly placeholder="Nhập số điện thoại">

        <label>Tỉnh/TP:</label>
        <select name="city" id="city" class="editable" disabled required>
            <option value="">Tỉnh/Thành phố</option>
            <option value="TP. Hà Nội">TP. Hà Nội</option>
            <option value="Hải Phòng">Hải Phòng</option>
            <option value="Quảng Ninh">Quảng Ninh</option>
            <option value="Bắc Ninh">Bắc Ninh</option>
            <option value="Hưng Yên">Hưng Yên</option>
            <option value="Thái Nguyên">Thái Nguyên</option>
            <option value="Tuyên Quang">Tuyên Quang</option>
            <option value="Lào Cai">Lào Cai</option>
            <option value="Phú Thọ">Phú Thọ</option>
            <option value="Ninh Bình">Ninh Bình</option>
            <option value="Điện Biên">Điện Biên</option>
            <option value="Lai Châu">Lai Châu</option>
            <option value="Sơn La">Sơn La</option>
            <option value="Cao Bằng">Cao Bằng</option>
            <option value="Lạng Sơn">Lạng Sơn</option>
            <option value="TP. Huế">TP. Huế</option>
            <option value="Đà Nẵng">Đà Nẵng</option>
            <option value="Thanh Hóa">Thanh Hóa</option>
            <option value="Nghệ An">Nghệ An</option>
            <option value="Hà Tĩnh">Hà Tĩnh</option>
            <option value="Quảng Trị">Quảng Trị</option>
            <option value="Quảng Ngãi">Quảng Ngãi</option>
            <option value="Khánh Hòa">Khánh Hòa</option>
            <option value="Gia Lai">Gia Lai</option>
            <option value="Đắk Lắk">Đắk Lắk</option>
            <option value="Lâm Đồng">Lâm Đồng</option>
            <option value="TP.HCM">TP.HCM</option>
            <option value="Cần Thơ">Cần Thơ</option>
            <option value="Đồng Nai">Đồng Nai</option>
            <option value="Tây Ninh">Tây Ninh</option>
            <option value="Vĩnh Long">Vĩnh Long</option>
            <option value="Đồng Tháp">Đồng Tháp</option>
            <option value="An Giang">An Giang</option>
            <option value="Cà Mau">Cà Mau</option>
        </select>
        <script>
            (function() {
                var savedCity = "${addr != null ? addr.province : ''}";
                if (savedCity) {
                    var citySelect = document.getElementById("city");
                    if (citySelect) {
                        citySelect.value = savedCity;
                    }
                }
            })();
        </script>
        <label>Quận/Huyện, Xã/Phường:</label>
        <input type="text" name="district" class="editable" placeholder="Quận/Huyện" value="${addr != null ? addr.ward : ''}" readonly>

        <label>Đường, Số nhà:</label>
        <input type="text" name="address" class="editable" placeholder="Số nhà, tên đường" value="${addr != null ? addr.address : ''}" readonly>

        <input type="hidden" name="addressId" value="${addr != null ? addr.id : 0}">

        <button type="button" id="btnToggle" class="btn-update">Cập nhật thông tin</button>
    </form>
</div>
<script src="${pageContext.request.contextPath}/assets/js/info.js"></script>
