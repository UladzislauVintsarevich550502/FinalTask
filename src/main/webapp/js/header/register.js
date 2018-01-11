$('input[type="registerSubmit"]').mousedown(function () {
    $(this).css('background', '#2ecc71');
});
$('input[type="registerSubmit"]').mouseup(function () {
    $(this).css('background', '#1abc9c');
});

$(document).click(function (e) {
    if (!$(e.target).closest(".register").length) {
        if($("#registerForm").is(e.target)) {
            if (!$('.register').is(':visible')) {
                $('.register').fadeToggle('slow');
                $(this).toggleClass('loginGreen');
            } else {
                $('.register').fadeToggle('slow');
                $('#registerForm').removeClass('loginGreen');
            }
        }else {
            if ($('.register').is(':visible')) {
                $('.register').fadeToggle('slow');
                $(this).toggleClass('loginGreen');
            }
        }
    }
    e.stopPropagation();
});

