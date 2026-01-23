document.addEventListener("DOMContentLoaded", function () {

    const checkboxes = document.querySelectorAll(".item-checkbox");
    const totalDisplay = document.getElementById("cart-total");
    const selectAllBtn = document.querySelector(".select-all-cart");
    const cartForm = document.getElementById("cart-form");

    function updateDisplayTotal() {
        let total = 0;
        const checkedBoxes = document.querySelectorAll(".item-checkbox:checked");

        checkedBoxes.forEach(box => {
            total += parseFloat(box.dataset.subtotal);
        });

        if (totalDisplay) {
            totalDisplay.innerText = total.toLocaleString('vi-VN') + 'đ';
        }
    }

    checkboxes.forEach(cb => {
        cb.addEventListener("change", updateDisplayTotal);
    });

    if (selectAllBtn) {
        selectAllBtn.addEventListener("click", function (e) {
            e.preventDefault();
            const allChecked = [...checkboxes].every(cb => cb.checked);
            checkboxes.forEach(cb => cb.checked = !allChecked);
            updateDisplayTotal();
            this.textContent = !allChecked ? "Bỏ chọn tất cả" : "Chọn tất cả";
        });
    }

    const clearAllBtn = document.querySelector(".clear-all-cart");
    if (clearAllBtn) {
        clearAllBtn.addEventListener("click", function (e) {
            if (!confirm("Bạn có chắc chắn muốn xóa toàn bộ giỏ hàng không?")) {
                e.preventDefault();
            }
        });
    }

    if (cartForm) {
        cartForm.addEventListener("submit", function(e) {
            const checkedBoxes = document.querySelectorAll(".item-checkbox:checked");
            if (checkedBoxes.length === 0) {
                e.preventDefault();
                alert("Vui lòng chọn ít nhất một sản phẩm để thanh toán!");
            }
        });
    }

    const qtyInputs = document.querySelectorAll(".product-quantity input[type='number']");
    qtyInputs.forEach(input => {
        input.addEventListener("keypress", function (e) {
            if (e.key === "Enter") {
                e.preventDefault();
                this.blur();
            }
        });
    });

    updateDisplayTotal();
});