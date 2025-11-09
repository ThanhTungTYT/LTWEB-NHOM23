const minus = document.getElementById("count-minus");
const add = document.getElementById("count-add");
const count  = document.getElementById("num-count");

minus.addEventListener("click", ev => {
    let current = parseInt(count.textContent);
    if(current>1){
        count.textContent = current - 1;
    }
})

add.addEventListener("click", ev => {
    let current = parseInt(count.textContent);
        count.textContent = current + 1;
})

const product = document.getElementById('product-catalog');
const relativeItems = [
    { name: 'Rumi-Cafe đóng gói Trung Nguyên', image: '../webapp/img/Cafe1.png', price: '100,000 VND'},
    { name: 'Drip Coffee', image: '../webapp/img/Cafe2.jpg', price: '200,000 VND'},
    { name: 'Cafe Chất-Vina Cafe', image: '../webapp/img/Cafe3.png', price: '300,000 VND'},
    { name: 'Fresh Coffee-Cafe đóng gói Archievs', image: '../webapp/img/Cafe4.jpeg', price: '400,000 VND'},
];
relativeItems.forEach(item => {
    const productItem = document.createElement('a');
    productItem.className = 'product';
    productItem.href = '#';
    productItem.innerHTML = `
    <img src="${item.image}" alt="${item.name}">
    <p>${item.name}</p>
    <span>${item.price}</span>
  `;
    product.appendChild(productItem);
});

document.addEventListener("DOMContentLoaded", function() {
    const toggleBtn = document.getElementById('readMoreBtn');
    const content = document.getElementById('contentToCollapse');
    const container = document.getElementById('productDescription');
    if (toggleBtn && content && container) {
        toggleBtn.addEventListener('click', function() {
            container.classList.toggle('is-expanded');
            if (container.classList.contains('is-expanded')) {
                content.style.maxHeight = content.scrollHeight + 'px';
                this.innerHTML = 'Thu gọn <span class="arrow"></span>';
            } else {
                content.style.maxHeight = null;
                this.innerHTML = 'Xem thêm <span class="arrow"></span>';
            }
        });
    } else {
        console.warn("Không tìm thấy các phần tử để thực hiện chức năng 'Xem thêm'.");
    }
});

document.addEventListener('DOMContentLoaded', function() {
    const mainProductImage = document.getElementById('img-main');
    const thumbnails = document.querySelectorAll('.thumbnail-item');

    thumbnails.forEach(thumbnail => {
        thumbnail.addEventListener('click', function() {
            thumbnails.forEach(item => item.classList.remove('active'));
            this.classList.add('active');
            const fullImageUrl = this.getAttribute('data-full-image');
            mainProductImage.src = fullImageUrl;
        });
    });
});