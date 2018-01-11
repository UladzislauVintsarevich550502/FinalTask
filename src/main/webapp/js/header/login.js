$('input[type="loginSubmit"]').click(function () {
    $(this).css('background', '#2ecc71');

});
$('input[type="loginSubmit"]').click(function () {
    $(this).css('background', '#1abc9c');
});


$(document).click(function (e) {
    if (!$(e.target).closest(".login").length) {
        if($("#loginForm").is(e.target)) {
            if (!$('.login').is(':visible')) {
                $('.login').fadeToggle('slow');
                $(this).toggleClass('loginGreen');
            } else {
                $('.login').fadeToggle('slow');
                $('#loginForm').removeClass('loginGreen');
            }
        }else {
            if ($('.login').is(':visible')) {
                $('.login').fadeToggle('slow');
                $(this).toggleClass('loginGreen');
            }
        }
    }
    e.stopPropagation();
});
