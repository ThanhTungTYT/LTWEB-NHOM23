const img_home = document.getElementById('img-home');
const images = window.BANNERS || [];

let currentIndex = 0;

if (images.length > 0) {
    img_home.style.backgroundImage = `url("${images[0]}")`;
}

function showNextImage() {
    currentIndex = (currentIndex + 1) % images.length;
    img_home.style.backgroundImage = `url("${images[currentIndex]}")`;
}

function showPreviousImage() {
    currentIndex = (currentIndex - 1 + images.length) % images.length;
    img_home.style.backgroundImage = `url("${images[currentIndex]}")`;
}

setInterval(showNextImage, 3000);

window.addEventListener("scroll", () => {
    const elements = document.querySelectorAll(".fade-in, .slide-up");
    elements.forEach((el) => {
        const rect = el.getBoundingClientRect();
        if (rect.top < window.innerHeight - 100) {
            el.classList.add("visible");
        }
    });
});




