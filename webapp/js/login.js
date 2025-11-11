document.addEventListener("DOMContentLoaded", function () {
    const blogin = document.getElementById('b-login');
    const usernameIP = document.getElementById('username');
    const passIP = document.getElementById('password');

    if (blogin && usernameIP && passIP) {
        blogin.addEventListener('click', function (envent) {
            if (usernameIP.value.trim() === '' || passIP.value.trim() === '') {
                alert('vui long dien day du thong tin')
                return;
            }
            const usename = usernameIP.value.trim().toLocaleLowerCase();
            const  pass = passIP.value.trim().toLocaleLowerCase();
            if (usename === 'admin'&& pass === '123123') {
                window.location.href = "../../templates/adminIndex.html";
            } else {
                window.location.href = '../../templates/account.html';
            }
        });
    }
});