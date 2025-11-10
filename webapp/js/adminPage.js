const btn = document.getElementById("slider-menu");
const menu = document.getElementById("left-menu");
const content = document.getElementById("right-content");
const btn_add = document.getElementById("add");
const btn_off = document.getElementById("take-off");
const form_add = document.getElementById("form-add");

btn.addEventListener('click', e => {
    if (menu.style.display !== "none") {
        menu.style.display = "none";
        content.style.width = "100%";
        form_add.style.left = "50%";
    } else {
        menu.style.display = "";
        content.style.width = "80%";
        form_add.style.left = "60%";
    }
});

const btn_slider = document.getElementById("slide-top");
window.addEventListener("scroll", () => {
    if (window.scrollY > 20) {
        btn_slider.style.display = "block";
    } else {
        btn_slider.style.display = "none";
    }
});
btn_slider.addEventListener("click", () => {
    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
});

btn_add.addEventListener('click', e => {
    if (form_add.style.display === 'none'){
        form_add.style.display = 'block';
        content.style.filter = 'blur(5px)';
    }
})
btn_off.addEventListener('click', e => {
    if (form_add.style.display === 'block'){
        form_add.style.display = 'none';
        content.style.filter = 'blur(0)';
    }
})