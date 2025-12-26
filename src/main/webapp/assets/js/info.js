$(document).ready(function() {
    $('#btnToggle').on('click', function(e) {
        var btn = $(this);
        var inputs = $('.info-form .editable');

        if (inputs.first().prop('readonly')) {
            e.preventDefault();

            inputs.prop('readonly', false);

            inputs.first().focus();

            btn.text('Lưu thay đổi');
            btn.addClass('btn-save-mode');

            btn.attr('type', 'submit');
        }
    });

    $('#userForm').on('submit', function(e) {
        e.preventDefault();

        var form = $(this);
        var url = form.attr('action');

        $.ajax({
            type: "POST",
            url: url,
            data: form.serialize(),
            success: function(response) {
                $('#content-area').html(response);
                alert("Cập nhật thông tin thành công!");
            },
            error: function() {
                alert("Có lỗi xảy ra. Vui lòng thử lại.");
            }
        });
    });
});