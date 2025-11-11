document.addEventListener("DOMContentLoaded", function () {
    const cartList = document.getElementById("cart-list");
    const totalDisplay = document.getElementById("cart-total");
    const clearAllBtn = document.querySelector(".clear-all-cart");

    attachEventsToAllItems();
    updateCartTotal();

    //  Gắn sự kiện cho tất cả sản phẩm có sẵn trong HTML
    function attachEventsToAllItems() {
        document.querySelectorAll(".cart-item").forEach(attachEvents);
    }

    function attachEvents(item) {
        const btnMinus = item.querySelector(".btn-decrease");
        const btnPlus = item.querySelector(".btn-increase");
        const input = item.querySelector("input[type='number']");
        const checkbox = item.querySelector(".product-select");
        const removeBtn = item.querySelector(".product-remove");

        btnMinus.addEventListener("click", () => {
            if (input.value > 1) input.value--;
            updateCartTotal();
        });

        btnPlus.addEventListener("click", () => {
            input.value++;
            updateCartTotal();
        });

        input.addEventListener("change", () => {
            if (input.value < 1 || isNaN(input.value)) input.value = 1;
            updateCartTotal();
        });

        checkbox.addEventListener("change", updateCartTotal);
        removeBtn.addEventListener("click", () => {
            item.remove();
            updateCartTotal();
        });
    }

    //  Cập nhật tổng tiền
    function updateCartTotal() {
        let total = 0;
        document.querySelectorAll(".cart-item").forEach((item) => {
            const checkbox = item.querySelector(".product-select");
            const qty = parseInt(item.querySelector("input[type='number']").value);
            const price = parseInt(item.dataset.price);
            const subtotal = price * qty;
            item.querySelector(".product-subtotal").textContent = formatPrice(subtotal);

            if (checkbox.checked) total += subtotal;
        });
        totalDisplay.textContent = formatPrice(total);
    }

    //  Xóa tất cả
    clearAllBtn.addEventListener("click", (e) => {
        e.preventDefault();
        cartList.innerHTML = "";
        updateCartTotal();
    });

    function formatPrice(value) {
        return value.toLocaleString("vi-VN") + "đ";
    }
});
