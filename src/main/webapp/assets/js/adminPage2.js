function addImageUrl() {
    var input = document.getElementById("urlInput");
    var url = input.value.trim();
    var countSpan = document.getElementById("countImg");
    var hiddenArea = document.getElementById("hidden-area");
    if (url === "") {
        alert("Vui lòng nhập link ảnh!");
        return;
    }
    var hiddenInput = document.createElement("input");
    hiddenInput.type = "hidden";
    hiddenInput.name = "image_urls";
    hiddenInput.value = url;
    hiddenArea.appendChild(hiddenInput);
    var currentCount = parseInt(countSpan.innerText);
    countSpan.innerText = currentCount + 1;
    input.value = "";
    alert("Đã thêm ảnh vào danh sách chờ!");
}
function addCat() {
    var popup = document.getElementById('form-add-cat');
    var content = document.getElementById('right-content');

    if (popup) {
        popup.style.display = 'block';
        // Làm mờ nền phía sau (nếu muốn)
        if (content) content.style.filter = 'blur(5px)';
    } else {
        console.error("Lỗi: Không tìm thấy ID form-add-cat");
    }
}

function dongFormThemLoai() {
    var popup = document.getElementById('form-add-cat');
    var content = document.getElementById('right-content');

    if (popup) {
        popup.style.display = 'none';
        // Bỏ làm mờ nền
        if (content) content.style.filter = 'none';
    }
}
function deleteCategory(id) {
    if (confirm("CẢNH BÁO: Bạn có chắc chắn muốn xóa loại sản phẩm này?\n(Lưu ý: Nếu loại này đang chứa sản phẩm thì sẽ xóa các sản phẩm có loại này)")) {
        // Điền ID vào form ẩn
        document.getElementById('input-cat-id').value = id;
        // Gửi form đi
        document.getElementById('form-delete-cat').submit();
    }
}
