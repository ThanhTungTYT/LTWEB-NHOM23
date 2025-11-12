
document.addEventListener("DOMContentLoaded", function() {

    const catalogPage = document.getElementById('catalog-list');

    if (catalogPage) {
        const menuItems = document.querySelectorAll('.catalog-item');
        const mainContentArea = document.getElementById('product-list');

        const categoryFiles = {
            "Tất cả": "../templates/catalog-all.html",
            "Cà phê hữu cơ": "../templates/catalog-cphc.html",
            "Cà phê nguyên hạt rang": "../templates/catalog-cpnh.html",
            "Cà phê hòa tan": "../templates/catalog-cpht.html",
            "Các sản phẩm đặc biệt": "../templates/catalog-special.html"
        };

        async function loadContent(url, title) {
            mainContentArea.innerHTML = '<div class="loading-state" style="text-align: center; padding: 50px;">Đang tải sản phẩm...</div>';

            console.log(`Đang tải: ${title}`);

                const response = await fetch(url);
                const htmlContent = await response.text();
                mainContentArea.innerHTML = htmlContent;
                console.log(`Tải thành công: ${url}`);

        }
        menuItems.forEach(link => {
            link.addEventListener('click', function(e) {
                e.preventDefault();

                const title = this.textContent.trim();
                const url = categoryFiles[title];

                menuItems.forEach(item => item.classList.remove('active'));
                this.classList.add('active');

                loadContent(url, title);
            });
        });

        const defaultTitle = "Tất cả";
        const defaultUrl = categoryFiles[defaultTitle];
        if (defaultUrl) {
            loadContent(defaultUrl, defaultTitle);
        }
    }

});