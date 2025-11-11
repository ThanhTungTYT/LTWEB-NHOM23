// adminPage.js

$(document).ready(function() {
    // === Các Phần tử Chính ===
    const leftMenu = $('#left-menu');
    const rightContent = $('#right-content');
    const sliderMenuButton = $('#slider-menu');
    const slideTopButton = $('#slide-top');
    const mainContentArea = $('#main-content');
    const pageTitleElement = $('#page-title');
    const menuItems = $('.menu-item');

    // Popups (Được đặt trong adminIndex.html)
    const detailPanel = $('#detail-p');
    const closeDetailButton = $('#close');
    const addProductFormContainer = $('#form-add');
    const closeFormButton = $('#take-off');

    // === Hàm Tải Nội dung bằng AJAX ===
    function loadContent(url, title) {
        // 1. Reset trạng thái Popups
        detailPanel.hide();
        addProductFormContainer.hide();

        // 2. Hiển thị trạng thái tải
        mainContentArea.html('<div class="loading-state">Đang tải nội dung...</div>');

        // 3. Cập nhật tiêu đề hiển thị
        pageTitleElement.text(title.toUpperCase());

        // 4. Thực hiện $.load()
        mainContentArea.load(url, function(response, status, xhr) {
            if (status === "error") {
                mainContentArea.html(`<div class='error-state'>Lỗi tải nội dung: Không tìm thấy file <strong>${url}</strong>.</div>`);
            } else {
                console.log(`Tải thành công: ${url}`);
                // 5. Gắn lại sự kiện cho các nút chức năng sau khi nội dung được tải
                attachDynamicListeners(title);
            }
        });
    }

    // === Gắn Sự kiện Động (Phải chạy lại sau mỗi lần load AJAX) ===
    function attachDynamicListeners(pageTitle) {

        // 1. Cho trang Quản lý Đơn hàng
        if (pageTitle.includes('Đơn hàng')) {
            const detailButtons = mainContentArea.find('.detail'); // Tìm nút trong nội dung vừa tải

            // Mở panel chi tiết
            detailButtons.off('click').on('click', function(e) {
                e.preventDefault();
                detailPanel.show();
            });
        }

        // 2. Cho trang Quản lý Sản phẩm
        if (pageTitle.includes('Sản phẩm')) {
            const openAddFormButton = mainContentArea.find('#add');

            // Mở form thêm sản phẩm
            openAddFormButton.off('click').on('click', function(e) {
                e.preventDefault();
                addProductFormContainer.show();
            });
        }

        // 3. Cho trang Quản lý Tài khoản (Xử lý nút xóa)
        if (pageTitle.includes('Tài khoản')) {
            const deleteButtons = mainContentArea.find('.list-account tbody button');
            deleteButtons.off('click').on('click', function(e) {
                e.preventDefault();
                const row = $(this).closest('tr');
                const accountName = row.children().eq(1).text();

                if (confirm(`Bạn có chắc chắn muốn xóa tài khoản của ${accountName} không?`)) {
                    console.log(`Đã xác nhận xóa tài khoản: ${accountName}`);
                    // Thực hiện AJAX DELETE tại đây
                    // row.remove();
                }
            });
        }
    }

    // === Xử lý Chuyển đổi Menu (Toggle Sidebar) ===
    sliderMenuButton.on('click', function() {
        leftMenu.toggleClass('collapsed');
        rightContent.toggleClass('expanded');
    });

    // === Xử lý Cuộn lên đầu trang ===
    if (slideTopButton.length) {
        $(window).on('scroll', function() {
            if ($(window).scrollTop() > 300) {
                slideTopButton.fadeIn();
            } else {
                slideTopButton.fadeOut();
            }
        });

        slideTopButton.on('click', function() {
            $('html, body').animate({ scrollTop: 0 }, 'slow');
        });
    }

    // === Xử lý Đóng Popups (Logic tĩnh) ===
    closeDetailButton.on('click', function() {
        detailPanel.hide();
    });

    closeFormButton.on('click', function() {
        addProductFormContainer.hide();
    });

    // === Xử lý Click Menu Item (AJAX Navigation) ===
    menuItems.on('click', function(e) {
        e.preventDefault();

        const url = $(this).attr('href');
        const title = $(this).data('title');

        // Cập nhật trạng thái active
        menuItems.removeClass('active');
        $(this).addClass('active');

        // Tải nội dung
        loadContent(url, title);
    });

    // === Khởi tạo Trang Mặc Định ===
    // Tải trang Tổng quan (Dashboard) khi vào lần đầu
    const defaultLink = $('a[data-title="Tổng quan"]');
    if (defaultLink.length) {
        loadContent(defaultLink.attr('href'), defaultLink.data('title'));
    }

});