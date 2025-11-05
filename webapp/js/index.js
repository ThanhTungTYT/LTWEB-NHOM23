const img_home = document.getElementById('img-home');
const images = [
    '../webapp/img/img_1.jpg',
    '../webapp/img/img_2.jpg',
    '../webapp/img/img_3.jpg'
];
const product = document.getElementById('product-list');
const hotItems = [
    { name: 'Rumi-Cafe đóng gói Trung Nguyên', image: '../webapp/img/Cafe1.png', price: '100,000 VND', purchase: 4500 },
    { name: 'Drip Coffee', image: '../webapp/img/Cafe2.jpg', price: '200,000 VND', purchase: 3500 },
    { name: 'Cafe Chất-Vina Cafe', image: '../webapp/img/Cafe3.png', price: '300,000 VND', purchase: 2500 },
    { name: 'Fresh Coffee-Cafe đóng gói Archievs', image: '../webapp/img/Cafe4.jpeg', price: '400,000 VND', purchase: 1500 },
    {},
    {},
    {},
    {},
    {},
    {}
];

let currentIndex = 0;

function showNextImage() {
    currentIndex++;
    if (currentIndex >= images.length) {
        currentIndex = 0;
    }
    img_home.style.backgroundImage = `url('${images[currentIndex]}')`;
}

function showPreviousImage() {
    currentIndex--;
    if (currentIndex < 0) {
        currentIndex = images.length - 1;
    }
    img_home.style.backgroundImage = `url('${images[currentIndex]}')`;
}

setInterval(showNextImage, 3000);

hotItems.forEach(item => {
    const productItem = document.createElement('a');
    productItem.className = 'product';
    productItem.href = '#';
    productItem.innerHTML = `
    <img src="${item.image}" alt="${item.name}">
    <p>${item.name}</p>
    <span>${item.price}</span>
    <label><i class="fa-solid fa-fire"></i>${item.purchase}</label>
  `;
    product.appendChild(productItem);
});

const btnLeft = document.getElementById("slider-left");
const btnRight = document.getElementById("slider-right");

btnRight.addEventListener("click", () => {
    product.scrollLeft += 300;
});

btnLeft.addEventListener("click", () => {
    product.scrollLeft -= 300;
});




