document.addEventListener("DOMContentLoaded", function () {
    const modal = document.getElementById("form-promotion");
    const btnAdd = document.getElementById("btn-open-add");
    const btnClose = document.getElementById("take-off");
    const content = document.getElementById("right-content");

    const formTitle = document.getElementById("popup-title");
    const inputAction = document.getElementById("input-action");
    const inputId = document.getElementById("input-id");
    const inputCode = document.getElementById("input-code");
    const inputDesc = document.getElementById("input-description");
    const inputMin = document.getElementById("input-min");
    const inputDiscount = document.getElementById("input-discount");
    const inputQuantity = document.getElementById("input-quantity");
    const inputStart = document.getElementById("input-start");
    const inputEnd = document.getElementById("input-end");
    const inputState = document.getElementById("input-state");


    function showModal() {
        if (modal) {
            modal.style.display = "block";
            if (content) content.style.filter = "blur(5px)";
        }
    }

    function hideModal() {
        if (modal) {
            modal.style.display = "none";
            if (content) content.style.filter = "none";
        }
    }

    if (btnAdd) {
        btnAdd.addEventListener("click", function (e) {
            e.preventDefault();

            formTitle.innerText = "THÊM MÃ KHUYẾN MÃI";
            inputAction.value = "add";
            inputId.value = "";

            inputCode.value = "";
            inputDesc.value = "";
            inputMin.value = "";
            inputDiscount.value = "";
            inputQuantity.value = "";
            inputStart.value = "";
            inputEnd.value = "";
            inputState.value = "active";

            showModal();
        });
    }

    // --- 4. XỬ LÝ SỰ KIỆN NÚT "SỬA" (QUAN TRỌNG) ---
    document.body.addEventListener("click", function (e) {
        const editBtn = e.target.closest(".btn-edit");

        if (editBtn) {
            e.preventDefault();

            const data = editBtn.dataset;

            formTitle.innerText = "CẬP NHẬT MÃ #" + data.id;
            inputAction.value = "update";
            inputId.value = data.id;

            inputCode.value = data.code;
            inputDesc.value = data.desc;
            inputMin.value = data.min;
            inputDiscount.value = data.discount;
            inputQuantity.value = data.quantity;
            inputStart.value = data.start;
            inputEnd.value = data.end;
            inputState.value = data.state;

            showModal();
        }
    });

    if (btnClose) {
        btnClose.addEventListener("click", function (e) {
            e.preventDefault();
            hideModal();
        });
    }
});