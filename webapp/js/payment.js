document.addEventListener("DOMContentLoaded", () => {
    const cartList = document.getElementById("cart-list");
    const totalPriceEl = document.getElementById("total-price");
    const finalTotalEl = document.getElementById("final-total");
    const checkoutBtn = document.getElementById("checkout-btn");

    // database sản phẩm (có thể mở rộng sau)
    const cartItems = [
        { name: "Cà phê rang nguyên chất 1", type: "Nguyên hạt", price: 200000, image: "../webapp/img/Cafe1.png" , qty: 1},
        { name: "Drip Coffee", type: "Phin giấy", price: 150000, image: "../webapp/img/Cafe2.jpg" , qty: 1},
        { name: "Cafe Chất - Vina Cafe", type: "Đậm vị", price: 250000, image: "../webapp/img/Cafe3.png" , qty: 1},
    ];

    // Render sản phẩm
    function renderCart() {
        cartList.innerHTML = "";
        cartItems.forEach((item, index) => {
            const el = document.createElement("div");
            el.className = "cart-item";
            el.innerHTML = `
        <img src="${item.image}" alt="${item.name}">
        <div class="cart-info">
          <p class="title">${item.name}</p>
          <p class="variant">${item.type}</p>
          <p class="price">${format(item.price)}</p>
        </div>
        <div class="quantity-box">
          <button class="btn-minus" data-index="${index}">-</button>
          <input type="number" value="${item.qty}" min="1">
          <button class="btn-plus" data-index="${index}">+</button>
        </div>
        <button class="remove-btn" data-index="${index}">×</button>
      `;
            cartList.appendChild(el);
        });
        attachEvents();
        updateTotal();
    }

    function attachEvents() {
        document.querySelectorAll(".btn-minus").forEach(btn => {
            btn.onclick = () => changeQty(btn.dataset.index, -1);
        });
        document.querySelectorAll(".btn-plus").forEach(btn => {
            btn.onclick = () => changeQty(btn.dataset.index, 1);
        });
        document.querySelectorAll(".remove-btn").forEach(btn => {
            btn.onclick = () => removeItem(btn.dataset.index);
        });
    }

    function changeQty(index, delta) {
        const item = cartItems[index];
        item.qty = Math.max(1, item.qty + delta);
        renderCart();
    }

    function removeItem(index) {
        cartItems.splice(index, 1);
        renderCart();
    }

    function updateTotal() {
        let total = cartItems.reduce((sum, item) => sum + item.price * item.qty, 0);
        const discount = getDiscount(total);
        totalPriceEl.textContent = format(total);
        finalTotalEl.textContent = format(total - discount);
    }

    // Giảm giá theo điều kiện
    function getDiscount(total) {
        const select = document.getElementById("discount-select");
        const percent = parseInt(select.value) || 0;
        if ((percent === 10 && total >= 500000) ||
            (percent === 15 && total >= 1000000) ||
            (percent === 20 && total >= 1500000)) {
            return (total * percent) / 100;
        }
        return 0;
    }

    document.getElementById("discount-select").addEventListener("change", updateTotal);

    // Xử lý đặt hàng
    checkoutBtn.addEventListener("click", (e) => {
        e.preventDefault();
        if (!validateForm()) return;

        alert("Đặt hàng thành công!");
    });

    // Kiểm tra form
    function validateForm() {
        const name = document.getElementById("fullname");
        const phone = document.getElementById("phone");
        const address = document.getElementById("address");
        let valid = true;

        [name, phone, address].forEach(input => {
            const err = input.nextElementSibling;
            if (!input.value.trim()) {
                err.textContent = "Vui lòng điền thông tin.";
                err.style.color = "red";
                valid = false;
            } else err.textContent = "";
        });

        if (!valid) alert("Vui lòng điền đầy đủ thông tin giao hàng.");
        return valid;
    }

    // Format giá tiền
    function format(value) {
        return value.toLocaleString("vi-VN") + "₫";
    }

    renderCart();

});
