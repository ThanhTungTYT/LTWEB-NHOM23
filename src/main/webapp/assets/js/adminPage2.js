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