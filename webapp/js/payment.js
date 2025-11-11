document.addEventListener("DOMContentLoaded", () => {
    const totalPriceEl = document.getElementById("total-price");
    const finalTotalEl = document.getElementById("final-total");
    const discountSelect = document.getElementById("discount-select");
    const checkoutBtn = document.getElementById("checkout-btn");

    const cartItems = document.querySelectorAll(".cart-item");

    cartItems.forEach((item) => {
        const minus = item.querySelector(".btn-minus");
        const plus = item.querySelector(".btn-plus");
        const qtyInput = item.querySelector("input[type='number']");
        const removeBtn = item.querySelector(".remove-btn");

        minus.addEventListener("click", () => {
            let qty = parseInt(qtyInput.value) || 1;
            qtyInput.value = qty > 1 ? qty - 1 : 1;
            updateTotal();
        });

        plus.addEventListener("click", () => {
            let qty = parseInt(qtyInput.value) || 1;
            qtyInput.value = qty + 1;
            updateTotal();
        });

        qtyInput.addEventListener("input", updateTotal);

        removeBtn.addEventListener("click", () => {
            item.remove();
            updateTotal();
        });
    });

    // ===== TÍNH TỔNG TIỀN =====
    function updateTotal() {
        let total = 0;
        document.querySelectorAll(".cart-item").forEach(item => {
            const priceEl = item.querySelector(".price");
            const qtyInput = item.querySelector("input[type='number']");
            const price = Number(priceEl.dataset.price);
            const qty = Number(qtyInput.value);
            total += price * qty;
        });

        const discount = getDiscount(total);
        totalPriceEl.textContent = format(total);
        finalTotalEl.textContent = format(total - discount);
    }

    // ===== ÁP DỤNG GIẢM GIÁ =====
    function getDiscount(total) {
        const percent = parseInt(discountSelect.value) || 0;
        if ((percent === 10 && total >= 500000) ||
            (percent === 15 && total >= 1000000) ||
            (percent === 20 && total >= 1500000)) {
            return (total * percent) / 100;
        }
        return 0;
    }

    discountSelect.addEventListener("change", updateTotal);
    updateTotal();

    // ===== KIỂM TRA THÔNG TIN THANH TOÁN =====
    checkoutBtn.addEventListener("click", (e) => {
        e.preventDefault();
        if (!validateForm()) {alert("Vui lòng điền đầy đủ thông tin...")
            return;}
        alert("Thanh toán đơn hàng thành công! Trở về trang chủ...");
        setTimeout(() => {
            window.location.href = "index.html";
        }, 1500);
    });

    function validateForm() {
        const name = document.getElementById("fullname");
        const phone = document.getElementById("phone");
        const country  = document.getElementById("country");
        const address = document.getElementById("address");
        const province = document.getElementById("province");
        let valid = true;

        [name, phone, country ,address, province].forEach(input => {
            const err = input.nextElementSibling;
            if (!input.value.trim()) {
                err.textContent = "Vui lòng điền thông tin.";
                err.style.color = "red";
                valid = false;
            } else {
                err.textContent = "";
            }
        });

        return valid;
    }

    // ===== FORMAT TIỀN =====
    function format(value) {
        return value.toLocaleString("vi-VN") + "₫";
    }
});
