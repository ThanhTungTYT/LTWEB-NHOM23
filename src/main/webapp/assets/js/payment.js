document.addEventListener("DOMContentLoaded", () => {

    const promotionSelect = document.getElementById("promotionSelect");
    const totalEl = document.getElementById("total-price");
    const finalEl = document.getElementById("final-total");
    const shippingEl = document.getElementById("shipping-fee");

    if (!promotionSelect || !totalEl || !finalEl || !shippingEl) return;

    const total = parseFloat(totalEl.dataset.total);
    const shipping = parseFloat(shippingEl.dataset.fee);

    function formatVND(value) {
        return value.toLocaleString("vi-VN") + " ₫";
    }

    function updateTotal() {
        const selectedOption =
            promotionSelect.options[promotionSelect.selectedIndex];

        const discountPercent =
            parseFloat(selectedOption.dataset.discount || 0);

        const discount = total * discountPercent / 100;
        const finalAmount = total - discount + shipping;

        finalEl.textContent = formatVND(finalAmount);
    }

    updateTotal();
    promotionSelect.addEventListener("change", updateTotal);
});
