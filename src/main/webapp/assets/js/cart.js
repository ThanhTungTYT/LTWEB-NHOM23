document.addEventListener("DOMContentLoaded", function () {

    // 1. CÁC HÀM XỬ LÝ CHECKBOX VÀ TỔNG TIỀN (UI PREVIEW)
    const checkboxes = document.querySelectorAll(".item-checkbox");
    const totalDisplay = document.getElementById("cart-total");
    const selectAllBtn = document.querySelector(".select-all-cart");

    function updateDisplayTotal() {
        let total = 0;
        const checkedBoxes = document.querySelectorAll(".item-checkbox:checked");

        checkedBoxes.forEach(box => {
            total += parseFloat(box.dataset.subtotal);
        });

        totalDisplay.innerText = total.toLocaleString('vi-VN') + 'đ';
    }

    // Gắn sự kiện change cho từng checkbox con
    checkboxes.forEach(cb => {
        cb.addEventListener("change", updateDisplayTotal);
    });

    // Xử lý nút Chọn tất cả
    if (selectAllBtn) {
        selectAllBtn.addEventListener("click", function (e) {
            e.preventDefault();
            // Logic check/uncheck như cũ
            const isAllChecked = [...checkboxes].every(cb => cb.checked);
            checkboxes.forEach(cb => cb.checked = !isAllChecked);

            // QUAN TRỌNG: Gọi lại hàm tính tiền sau khi chọn tất cả
            updateDisplayTotal();
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

    const qtyInputs = document.querySelectorAll(".product-quantity input[type='number']");
    qtyInputs.forEach(input => {
        input.addEventListener("keypress", function (e) {
            if (e.key === "Enter") {
                e.preventDefault();
                this.blur();
            }
        });
    });

});