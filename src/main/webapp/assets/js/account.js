// script.js

$(document).ready(function () {
    const contentArea = $('#content-area');

    function loadContent(url) {
        contentArea.html('<div class="loading-state">Đang tải nội dung...</div>');

        // Sử dụng $.load() để thực hiện AJAX
        contentArea.load(url, function (response, status, xhr) {
            if (status == "error") {
                contentArea.html(`<div style='color:red; padding:20px;'>
                    Lỗi kết nối: ${xhr.status} ${xhr.statusText}<br>
                    Đường dẫn gọi: ${url}
                </div>`);
            }
        });
    }
    let defaultLink = $('a[href="info"]');
    if (defaultLink.length > 0) {
        defaultLink.addClass('active');
        loadContent("info");
    }
    $('.sidebar-link').on('click', function (e) {
        e.preventDefault();

        $('.sidebar-link').removeClass('active');
        $(this).addClass('active');

        var urlToLoad = $(this).attr('href');
        loadContent(urlToLoad);
    });

    // Xử lý Đăng xuất
    $('.logout-link').on('click', function (e) {
        alert("Đang đăng xuất....");
    });
});