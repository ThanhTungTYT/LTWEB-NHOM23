/*
 * File JavaScript chính cho trang Admin (Bản Nâng Cấp)
 * 1. Tự động tìm/dọn dẹp pop-up từ file con.
 * 2. Lắng nghe sự kiện trên "document.body".
 */
document.addEventListener('DOMContentLoaded', () => {

    // --- Lấy các phần tử CỐ ĐỊNH ---
    const menu = document.getElementById("left-menu");
    const content = document.getElementById("right-content");
    const menuContainer = document.querySelector(".menu");
    const btn_slider_top = document.getElementById("slide-top");


    /**
     * HÀM LOADCONTENT (ĐÃ NÂNG CẤP)
     * Tự động tải #right-content VÀ các pop-up
     */
    async function loadContent(url) {
        try {
            const response = await fetch(url);
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            const html = await response.text();
            const parser = new DOMParser();
            const doc = parser.parseFromString(html, 'text/html');

            // --- 1. Dọn dẹp Pop-up cũ ---
            // Tìm và xóa bất kỳ pop-up động nào đang tồn tại
            document.querySelectorAll('.dynamic-popup').forEach(popup => {
                popup.remove();
            });

            // --- 2. Tải Nội dung chính (#right-content) ---
            const newContent = doc.getElementById('right-content');
            if (newContent) {
                content.innerHTML = newContent.innerHTML;
                content.style.filter = 'blur(0)';
            } else {
                content.innerHTML = `<p style="color: red;">Lỗi: Không tìm thấy div#right-content trong file ${url}</p>`;
            }

            // --- 3. Tải các Pop-up động từ file con ---
            // Tìm pop-up trong file con...
            const formAddPopup = doc.getElementById('form-add');
            const detailPopup = doc.getElementById('detail-p');

            // Nếu tìm thấy, "nhấc" nó ra ngoài body và gắn tag để dọn dẹp
            if (formAddPopup) {
                formAddPopup.classList.add('dynamic-popup'); // Thêm class để dọn dẹp
                document.body.appendChild(formAddPopup);
            }

            if (detailPopup) {
                detailPopup.classList.add('dynamic-popup'); // Thêm class để dọn dẹp
                document.body.appendChild(detailPopup);
            }

        } catch (error) {
            console.error('Lỗi khi tải nội dung:', error);
            content.innerHTML = `<p>Lỗi: Không thể tải trang ${url}.</p>`;
        }
    }

    /**
     * Hàm phụ để cập nhật vị trí pop-up
     */
    function updatePopupPositions(leftValue) {
        // Tìm pop-up mỗi khi cần, vì chúng có thể không tồn tại
        const form_add = document.getElementById("form-add");
        const detail = document.getElementById("detail-p");
        if (form_add) form_add.style.left = leftValue;
        if (detail) detail.style.left = leftValue;
    }

    // --- Gắn các sự kiện ---

    // 1. Xử lý nhấp chuột vào MENU
    if (menuContainer) {
        menuContainer.addEventListener('click', e => {
            if (e.target.classList.contains('menu-item')) {
                e.preventDefault();
                const pageUrl = e.target.dataset.page;
                if (pageUrl) {
                    loadContent(pageUrl);
                    menuContainer.querySelectorAll('.menu-item').forEach(link => {
                        link.classList.remove('active');
                    });
                    e.target.classList.add('active');
                }
            }
        });
    }

    // 2. Xử lý nút "Slide Top"
    if (btn_slider_top) {
        window.addEventListener("scroll", () => {
            btn_slider_top.style.display = window.scrollY > 20 ? "block" : "none";
        });
        btn_slider_top.addEventListener("click", () => {
            window.scrollTo({ top: 0, behavior: "smooth" });
        });
    }

    // 3. XỬ LÝ SỰ KIỆN TOÀN TRANG
    // Lắng nghe trên `document.body`
    document.body.addEventListener('click', e => {
        // Tìm pop-up MỖI KHI CLICK, vì chúng được tạo động
        const form_add = document.getElementById("form-add");
        const detail = document.getElementById("detail-p");

        // a) Xử lý nút ẨN/HIỆN MENU
        const sliderMenu = e.target.closest('#slider-menu');
        if (sliderMenu) {
            if (getComputedStyle(menu).display !== "none") {
                menu.style.display = "none";
                content.style.width = "100%";
                updatePopupPositions("50%");
            } else {
                menu.style.display = "";
                content.style.width = "80%";
                updatePopupPositions("60%");
            }
        }

        // b) Xử lý nút MỞ form "Thêm"
        if (e.target.id === 'add') {
            if (form_add && getComputedStyle(form_add).display === 'none') {
                form_add.style.display = 'block';
                content.style.filter = 'blur(5px)';
            }
        }

        // c) Xử lý nút TẮT form "Thêm"
        if (e.target.id === 'take-off') {
            if (form_add && getComputedStyle(form_add).display === 'block') {
                form_add.style.display = 'none';
                content.style.filter = 'blur(0)';
            }
        }

        // d) Xử lý nút MỞ "Chi tiết"
        if (e.target.closest('.detail')) {
            if (detail && getComputedStyle(detail).display === 'none') {
                detail.style.display = 'flex';
                content.style.filter = 'blur(5px)';
            }
        }

        // e) Xử lý nút ĐÓNG "Chi tiết"
        if (e.target.id === 'close') {
            if (detail && getComputedStyle(detail).display === 'flex') {
                detail.style.display = 'none';
                content.style.filter = 'blur(0)';
            }
        }
    });

    // --- Tải nội dung trang đầu tiên ---
    loadContent('adminPage1.html');
});