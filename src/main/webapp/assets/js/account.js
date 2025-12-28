$(document).ready(function () {
    const contentArea = $('#content-area');

    // Hàm load nội dung
    function loadContent(url) {
        contentArea.html('<div style="text-align:center; padding:50px; color:#666;">Đang tải dữ liệu... <i class="fas fa-spinner fa-spin"></i></div>');

        contentArea.load(url, function (response, status, xhr) {
            if (status == "error") {
                contentArea.html(`<div style="color:red; padding:20px; text-align:center;">Lỗi kết nối: ${xhr.status} ${xhr.statusText}</div>`);
            }
        });
    }

    // Xử lý sự kiện click
    $('.sidebar-link').on('click', function (e) {
        e.preventDefault(); // Chặn chuyển trang

        // Đổi màu active
        $('.sidebar-link').removeClass('active');
        $(this).addClass('active');

        // Lấy link từ href và load
        var urlToLoad = $(this).attr('href');
        loadContent(urlToLoad);
    });

    $('.sidebar-link.active').trigger('click');
});