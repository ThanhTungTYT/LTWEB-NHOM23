const btn = document.getElementById("slider-menu");
const menu = document.getElementById("left-menu");
const content = document.getElementById("right-content");
const btn_add = document.getElementById("add");
const btn_off = document.getElementById("take-off");
const form_add = document.getElementById("form-add");
const close = document.getElementById("close");
const btn_on = document.querySelectorAll(".detail");
const detail = document.getElementById("detail-p");

if (btn) {
    btn.addEventListener('click', e => {
        if (getComputedStyle(menu).display !== "none") {
            menu.style.display = "none";
            content.style.width = "100%";
            if (form_add) form_add.style.left = "50%";
            if (detail) detail.style.left = "50%";
        } else {
            menu.style.display = "";
            content.style.width = "80%";
            if (form_add) form_add.style.left = "60%";
            if (detail) detail.style.left = "60%";
        }
    });
}

const btn_slider = document.getElementById("slide-top");
if (btn_slider) {
    window.addEventListener("scroll", () => {
        btn_slider.style.display = window.scrollY > 20 ? "block" : "none";
    });

    btn_slider.addEventListener("click", () => {
        window.scrollTo({ top: 0, behavior: "smooth" });
    });
}

if (btn_add && form_add && btn_off) {
    btn_add.addEventListener('click', e => {
        if (getComputedStyle(form_add).display === 'none') {
            form_add.style.display = 'block';
            content.style.filter = 'blur(5px)';
        }
    });

    btn_off.addEventListener('click', e => {
        if (getComputedStyle(form_add).display === 'block') {
            form_add.style.display = 'none';
            content.style.filter = 'blur(0)';
        }
    });
}

if (btn_on && detail && close) {
    btn_on.forEach(button => {
        button.addEventListener('click', e => {
            if (getComputedStyle(detail).display === 'none') {
                detail.style.display = 'flex';
                content.style.filter = 'blur(5px)';
            }
        });
    });

    close.addEventListener('click', e => {
        if (getComputedStyle(detail).display === 'flex') {
            detail.style.display = 'none';
            content.style.filter = 'blur(0)';
        }
    });
}
