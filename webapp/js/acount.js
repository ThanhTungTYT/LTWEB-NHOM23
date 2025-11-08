// script.js

$(document).ready(function() {
    const contentArea = $('#content-area');

    function loadContent(url) {
        contentArea.html('<div class="loading-state">Đang tải nội dung...</div>');

        // Sử dụng $.load() để thực hiện AJAX
        contentArea.load(url, function(response, status, xhr) {
            if (status == "error") {
                contentArea.html(`<div class='error-state'>Lỗi tải trang: Không tìm thấy file <strong>${url}</strong>. Vui lòng kiểm tra tên file.</div>`);
            }
        });
    }
    // 1. Đặt trạng thái 'active' cho nút "Thông tin cá nhân"
    $('.sidebar-link').removeClass('active');
    // Tìm liên kết có href là 'info.html' (hoặc dùng class active ban đầu nếu đã set trong html)
    const defaultLink = $('a[href="info.html"]');
    defaultLink.addClass('active');

    // 2. Tải nội dung mặc định (Thông tin cá nhân) ngay lập tức
    loadContent(defaultLink.attr('href'));

    //Xử lý click cho các liên kết menu (sidebar-link)
    $('.sidebar-link').on('click', function(e) {
        e.preventDefault(); // Ngăn chặn trình duyệt chuyển trang

        const url = $(this).attr('href');

        // Cập nhật trạng thái active
        $('.sidebar-link').removeClass('active');
        $(this).addClass('active');

        // Tải nội dung từ file tương ứng
        loadContent(url);
    });

    // Xử lý Đăng xuất
    $('.logout-link').on('click', function(e) {
        alert("Đang xử lý đăng xuất và chuyển hướng...");
        // Trình duyệt sẽ tự chuyển hướng nhờ href
    });
});