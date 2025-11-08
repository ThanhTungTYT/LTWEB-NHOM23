const products = [];
const categories = ['all', 'organic-coffee', 'roasted-coffee', 'instant-coffee', 'special-products'];
for (let i = 1; i <= 200; i++) {
    const validCategories = categories.slice(1);
    const temp = validCategories[(i - 1) % validCategories.length];
    let category
    switch (temp){
        case 'organic-coffee':
            category = 'Cà phê hữu cơ'
            break;
        case 'roasted-coffee':
            category = 'Cà phê hạt nguyên rang'
            break;
        case 'instant-coffee':
            category = 'Cà phê hòa tan'
            break;
        case 'special-products':
            category = 'Sản phẩm đặc biệt'
            break;
    }
    products.push({
        id: i,
        img: '',
        name: `Sản phẩm #${i}`,
        price: `${(Math.random() * 100 + 50).toFixed(0)}.000đ`,
        category: category,
        temp: temp
    });
}

const productsPerPage = 15;
let currentPage = 1;
let currentCategory = 'all';

function filterProducts() {
    if (currentCategory === 'all') return products;
    return products.filter(p => p.temp === currentCategory);
}

function renderProducts() {
    const list = document.getElementById('product-list');
    list.innerHTML = '';

    const filtered = filterProducts();
    const start = (currentPage - 1) * productsPerPage;
    const end = start + productsPerPage;
    const currentProducts = filtered.slice(start, end);

    currentProducts.forEach(p => {
        const div = document.createElement('a');
        div.classList.add('product');
        div.innerHTML = `
        <img src="${p.img}" alt="ảnh"></img>
        <p>${p.name}</p>
        <span>Giá: ${p.price}</span>
        <label>Loại: ${p.category}</label>
      `;
        list.appendChild(div);
    });

    renderPagination(filtered.length);
}

function renderPagination(totalItems) {
    const pagination = document.getElementById('product-page');
    pagination.innerHTML = '';

    const totalPages = Math.ceil(totalItems / productsPerPage);
    const maxVisible = 3;

    let start = currentPage - Math.floor(maxVisible / 2);
    let end = currentPage + Math.floor(maxVisible / 2);

    if (start < 1) {
        start = 1;
        end = Math.min(totalPages, maxVisible);
    }

    if (end > totalPages) {
        end = totalPages;
        start = Math.max(1, end - maxVisible + 1);
    }

    if (currentPage > 1) {
        const prev = document.createElement('button');
        prev.textContent = '←';
        prev.addEventListener('click', () => {
            currentPage--;
            renderProducts();
        });
        pagination.appendChild(prev);
    }

    for (let i = start; i <= end; i++) {
        const btn = document.createElement('button');
        btn.textContent = i;
        if (i === currentPage) btn.classList.add('active');
        btn.addEventListener('click', () => {
            currentPage = i;
            renderProducts();
        });
        pagination.appendChild(btn);
    }

    if (currentPage < totalPages) {
        const next = document.createElement('button');
        next.textContent = '→';
        next.addEventListener('click', () => {
            currentPage++;
            renderProducts();
        });
        pagination.appendChild(next);
    }
}

document.getElementById('list-catalog').addEventListener('click', e => {
    e.preventDefault();
    const link = e.target.closest('a');
    if (!link) return;
    document.querySelectorAll('#list-catalog a').forEach(a => a.classList.remove('active'));
    link.classList.add('active');
    currentCategory = link.getAttribute('data-category');
    currentPage = 1;
    renderProducts();
});
const urlParams = new URLSearchParams(window.location.search);
const urlCategory = urlParams.get('category');

if (urlCategory && categories.includes(urlCategory)) {
    currentCategory = urlCategory
}
renderProducts();