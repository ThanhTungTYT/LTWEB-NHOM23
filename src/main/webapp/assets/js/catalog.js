document.addEventListener("DOMContentLoaded", function () {

    const catalogPage = document.getElementById('catalog-list');

    if (catalogPage) {
        const menuItems = document.querySelectorAll('.catalog-item');
        const mainContentArea = document.getElementById('product-list');

        const categoryFiles = {
            "Tất cả": "../templates/catalog-all.html",
            "Cà phê hữu cơ": "../templates/catalog-cphc.html",
            "Cà phê rang nguyên hạt": "../templates/catalog-cpnh.html",
            "Cà phê xay nguyên chất": "../templates/catalog-cpxnc.html",
            "Các sản phẩm đặc biệt": "../templates/catalog-special.html"
        };

        async function loadContent(url, title) {
            mainContentArea.innerHTML = '<div class="loading-state" style="text-align: center; padding: 50px;">Đang tải sản phẩm...</div>';

            const response = await fetch(url);
            const htmlContent = await response.text();
            mainContentArea.innerHTML = htmlContent;
            setupPagination();
            console.log(`Tải thành công: ${url}`);

        }

        menuItems.forEach(link => {
            link.addEventListener('click', function (e) {
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

        function setupPagination() {
            const allProducts = document.querySelectorAll(".product");
            const itemsPerPage = 25;
            let currentPage = 1;
            const totalPages = Math.ceil(allProducts.length / itemsPerPage);

            const prev = document.getElementById("prev-page");
            const next = document.getElementById("next-page");
            const input = document.getElementById("page-input");

            function renderPage() {
                const start = (currentPage - 1) * itemsPerPage;
                const end = start + itemsPerPage;

                allProducts.forEach((p, i) => {
                    p.style.display = i >= start && i < end ? "flex" : "none";
                });

                input.value = currentPage;
                prev.disabled = currentPage === 1;
                next.disabled = currentPage === totalPages;
            }

            prev.onclick = () => {
                if (currentPage > 1) {
                    currentPage--;
                    renderPage();
                    window.scrollTo({top: 0, behavior: "smooth"});
                }
            };

            next.onclick = () => {
                if (currentPage < totalPages) {
                    currentPage++;
                    renderPage();
                    window.scrollTo({top: 0, behavior: "smooth"});
                }
            };

            input.addEventListener("keypress", (e) => {
                if (e.key === "Enter") {
                    let val = parseInt(input.value);
                    if (isNaN(val) || val < 1) val = 1;
                    if (val > totalPages) val = totalPages;
                    currentPage = val;
                    renderPage();
                    window.scrollTo({top: 0, behavior: "smooth"});
                }
            });

            renderPage();
        }
    }

});