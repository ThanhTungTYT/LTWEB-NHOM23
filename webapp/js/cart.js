document.addEventListener("DOMContentLoaded", function () {
    const cartList = document.getElementById("cart-list");
    const totalDisplay = document.getElementById("cart-total");
    const clearAllBtn = document.querySelector(".clear-all-cart");

    // 🧱 Danh sách sản phẩm mẫu (thêm trường "weight")
    const products = [
        { name: "Cà phê rang nguyên chất 1", type: "Nguyên hạt", weight: "500gr", price: 200000, image: "../webapp/img/Cafe1.png" },
        { name: "Drip Coffee", type: "Phin giấy", weight: "800gr", price: 150000, image: "../webapp/img/Cafe2.jpg" },
        { name: "Cafe Chất - Vina Cafe", type: "Đậm vị", weight: "1000gr", price: 250000, image: "../webapp/img/Cafe3.png" },
    ];

    products.forEach(addToCart);

    // 🛒 Hàm thêm sản phẩm vào giỏ hàng
    function addToCart(product) {
        const item = document.createElement("div");
        item.classList.add("cart-item");
        item.dataset.price = product.price;

        item.innerHTML = `
        <input type="checkbox" class="product-select">
        <div class="product-remove"><i class="fas fa-times"></i></div>
        <div class="product-thumbnail">
            <img src="${product.image}" alt="${product.name}" />
        </div>
        <div class="product-details">
            <p class="product-name">${product.name}</p>
            <p class="product-type">Loại: ${product.type}</p>
            <p class="product-weight">Khối lượng: <span>${product.weight}</span></p>
        </div>
        <div class="product-price">${formatPrice(product.price)}</div>
        <div class="product-quantity">
            <button class="btn-decrease">-</button>
            <input type="number" value="1" min="1" />
            <button class="btn-increase">+</button>
        </div>
        <div class="product-subtotal">${formatPrice(product.price)}</div>
        `;

        cartList.appendChild(item);
        attachEvents(item);
        updateCartTotal();
    }

    // 🎯 Gắn sự kiện cho từng sản phẩm
    function attachEvents(item) {
        const btnMinus = item.querySelector(".btn-decrease");
        const btnPlus = item.querySelector(".btn-increase");
        const input = item.querySelector("input[type='number']");
        const checkbox = item.querySelector(".product-select");
        const removeBtn = item.querySelector(".product-remove");

        btnMinus.addEventListener("click", () => {
            let value = parseInt(input.value);
            if (value > 1) input.value = value - 1;
            updateCartTotal();
        });

        btnPlus.addEventListener("click", () => {
            input.value = parseInt(input.value) + 1;
            updateCartTotal();
        });

        input.addEventListener("change", () => {
            if (parseInt(input.value) < 1 || isNaN(parseInt(input.value))) input.value = 1;
            updateCartTotal();
        });

        checkbox.addEventListener("change", updateCartTotal);

        removeBtn.addEventListener("click", () => {
            item.remove();
            updateCartTotal();
        });
    }

    // 💰 Cập nhật tổng tiền
    function updateCartTotal() {
        const cartItems = document.querySelectorAll(".cart-item");
        let total = 0;

        cartItems.forEach((item) => {
            const checkbox = item.querySelector(".product-select");
            const qty = parseInt(item.querySelector("input[type='number']").value);
            const price = parseInt(item.dataset.price);
            const subtotal = price * qty;
            item.querySelector(".product-subtotal").textContent = formatPrice(subtotal);

            if (checkbox.checked) total += subtotal;
        });

        totalDisplay.textContent = formatPrice(total);
    }

    // 🧹 Xóa toàn bộ giỏ hàng
    clearAllBtn.addEventListener("click", (e) => {
        e.preventDefault();
        cartList.innerHTML = "";
        updateCartTotal();
    });

    // 🪙 Định dạng tiền VNĐ
    function formatPrice(value) {
        return value.toLocaleString("vi-VN") + "đ";
    }
});
