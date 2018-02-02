jQuery(document).ready(function ($) {
    $('form#login-form').on('click', function (e) {

        $('#a-image-name').on('click', function () {
            document.getElementById('file').click();
        });
// Запрещаем стандартное поведение для кнопки submit
        var loginReg = new RegExp('^([a-zA-Z][a-zA-Z-_0-9]+)$');
        var passwordReg = new RegExp('[a-zA-Z-_0-9]{6,}');
        var $form_modal = $('.cd-user-modal');
        var $form_login = $form_modal.find('#login');
        var $numberCorrectField = 0;

        var password = $('input#signin-password').val();
        var login = $('input#signin-login').val();

        if (password.length >= 4 && password != '' && passwordReg.test(password)) {
            $('input#signin-password').css('border-color', 'green');
            $form_login.find('a#a-signin-password').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        }
        else {
            if (password.length != 0) {
                $('input#signin-password').css('border-color', 'red');
                $form_login.find('a#a-signin-password').addClass('has-error').next('span').addClass('is-visible');
            }
        }
        if (login.length > 3 && login != '' && loginReg.test(login)) {
            $('input#signin-login').css('border-color', 'green');
            $form_login.find('input#signin-login').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (login.length != 0) {
                $('input#signin-login').css('border-color', 'red');
                $form_login.find('input#signin-login').addClass('has-error').next('span').addClass('is-visible');
            }
        }
        if ($numberCorrectField != 2) {
            e.preventDefault();
        }
    });

    $('form#signup-form').on('click', function (e) {
        var name_surnameReg = new RegExp("^([\u{0410}-\u{042F}]{1}[\u{0430}-\u{044F}]+)$|^([A-Z]{1}[a-z]+)$");
        var loginReg = new RegExp('^([a-zA-Z][a-zA-Z-_0-9]+)$');
        var passwordReg = new RegExp('[a-zA-Z-_0-9]{6,}');
        var emailReg = new RegExp("^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$");
        var $form_modal = $('.cd-user-modal');
        var $form_signup = $form_modal.find('#cd-signup');

        var password = $('input#signup-password').val();
        var login = $('input#signup-login').val();
        var name = $('input#signup-name').val();
        var surname = $('input#signup-surname').val();
        var email = $('input#signup-email').val();
        var repassword = $('input#signup-rePassword').val();

        var $numberCorrectField = 0;

        if (password.length >= 4 && passwordReg.test(password)) {
            $('input#signup-password').css('border-color', 'green');
            $form_signup.find('a#a-signup-password').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (password.length != 0) {
                $('input#signup-password').css('border-color', 'red');
                $form_signup.find('a#a-signup-password').addClass('has-error').next('span').addClass('is-visible');
            }
        }


        if (login.length >= 3 && loginReg.test(login)) {
            $('input#signup-login').css('border-color', 'green');
            $form_signup.find('input#signup-login').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (login.length != 0) {
                $('input#signup-login').css('border-color', 'red');
                $form_signup.find('input#signup-login').addClass('has-error').next('span').addClass('is-visible');
            }
        }


        if (name.length >= 2 && name_surnameReg.test(name)) {
            $('input#signup-name').css('border-color', 'green');
            $form_signup.find('input#signup-name').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (name.length != 0) {
                $('input#signup-name').css('border-color', 'red');
                $form_signup.find('input#signup-name').addClass('has-error').next('span').addClass('is-visible');
            }
        }


        if (surname.length >= 2 && name_surnameReg.test(surname)) {
            $('input#signup-surname').css('border-color', 'green');
            $form_signup.find('input#signup-surname').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (surname.length != 0) {
                $('input#signup-surname').css('border-color', 'red');
                $form_signup.find('input#signup-surname').addClass('has-error').next('span').addClass('is-visible');
            }
        }

        if (repassword == password && repassword.length >= 4) {
            $('input#signup-rePassword').css('border-color', 'green');
            $form_signup.find('a#a-signup-rePassword').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (repassword.length != 0) {
                $('input#signup-rePassword').css('border-color', 'red');
                $form_signup.find('a#a-signup-rePassword').addClass('has-error').next('span').addClass('is-visible');
            }
        }

        if (email.length > 7 && email != '' && emailReg.test(email)) {
            $('input#signup-email').css('border-color', 'green');
            $form_signup.find('input#signup-email').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (email.length != 0) {
                $('input#signup-email').css('border-color', 'red');
                $form_signup.find('input#signup-email').addClass('has-error').next('span').addClass('is-visible');
            }
        }

        if ($numberCorrectField != 6) {
            e.preventDefault();
        }
    });

    $('form#add-product').on('click', function (e) {
        var t = event.target || event.srcElement;

        var name_ruReg = new RegExp('^([\u{0410}-\u{042F}]{1}[\u{0430}-\u{044F}]+)$');
        var name_enReg = new RegExp('^([A-Z]{1}[a-z]+)$');
        var costReg = new RegExp('^(([0-9]+).([0-9]+))$');
        var valueReg = new RegExp('^([0-9]+)$');

        var $form_modal = $('#add-form');
        var $form_add = $form_modal.find('#add-product');

        var product_type = $('select#product-type').val();
        var name_ru = $('input#name-ru').val();
        var name_en = $('input#name-en').val();
        var value = $('input#value').val();
        var cost = $('input#cost').val();
        var status = $('select#status').val();
        var image_name = $('input#image-name').val();
        var $numberCorrectField = 0;

        if (product_type != null) {
            $('select#product-type').css('border-color', 'green');
            $form_add.find('select#product-type').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('select#product-type').css('border-color', 'red');
            $form_add.find('select#product-type').addClass('has-error').next('span').addClass('is-visible');
        }

        if (name_ru.length >= 2 && name_ru != '' && name_ruReg.test(name_ru)) {
            $('input#name-ru').css('border-color', 'green');
            $form_add.find('input#name-ru').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (name_ru.length != 0) {
                $('input#name-ru').css('border-color', 'red');
                $form_add.find('input#name-ru').addClass('has-error').next('span').addClass('is-visible');
            }
        }

        if (name_en.length >= 2 && name_en != '' && name_enReg.test(name_en)) {
            $('input#name-en').css('border-color', 'green');
            $form_add.find('input#name-en').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (name_ru.length != 0) {
                $('input#name-en').css('border-color', 'red');
                $form_add.find('input#name-en').addClass('has-error').next('span').addClass('is-visible');
            }
        }

        if (cost != '' && costReg.test(cost)) {
            $('input#cost').css('border-color', 'green');
            $form_add.find('input#cost').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (cost.length != 0) {
                $('input#cost').css('border-color', 'red');
                $form_add.find('input#cost').addClass('has-error').next('span').addClass('is-visible');
            }
        }

        if (value != '' && valueReg.test(value)) {
            $('input#value').css('border-color', 'green');
            $form_add.find('input#value').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (value.length != 0) {
                $('input#value').css('border-color', 'red');
                $form_add.find('input#value').addClass('has-error').next('span').addClass('is-visible');
            }
        }

        if (status != null) {
            $('select#status').css('border-color', 'green');
            $form_add.find('select#status').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('select#status').css('border-color', 'red');
            $form_add.find('select#status').addClass('has-error').next('span').addClass('is-visible');
        }

        if (image_name != "Please, choose picture" && image_name != "\u0412\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u043A\u0430\u0440\u0442\u0438\u043D\u043A\u0443") {
            $('input#image-name').css('border-color', 'green');
            $form_add.find('a#a-image-name').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#image-name').css('border-color', 'red');
            $form_add.find('a#a-image-name').addClass('has-error').next('span').addClass('is-visible');
        }

        if ($numberCorrectField != 7 && t.id == "add") {
            e.preventDefault();
        }
    });

});