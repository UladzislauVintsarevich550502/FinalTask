jQuery(document).ready(function ($) {
    $('form#login-form').on('click', function (e) {
        // Запрещаем стандартное поведение для кнопки submit
        var loginReg = /[a-zA-Zа-яА-Я]+\w+/;
        var passwordReg = /\w{6,}/;
        var $form_modal = $('.cd-user-modal');
        var $form_login = $form_modal.find('#login');
        var $numberCorrectField = 0;

        var password = $('input#signin-password').val();
        var login = $('input#signin-login').val();

        if (password.length > 4 && password != '' && passwordReg.test(password)) {
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

        var name_surnameReg = /([A-Z][a-z]+)|([А-Я][а-я]+)$/i;
        var loginReg = /[a-zA-Zа-яА-Я]+\w+/;
        var passwordReg = /\w{6,}/;
        var emailReg = /^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$/;

        var $form_modal = $('.cd-user-modal');
        var $form_signup = $form_modal.find('#cd-signup');

        var password = $('input#signup-password').val();
        var login = $('input#signup-login').val();
        var name = $('input#signup-name').val();
        var surname = $('input#signup-surname').val();
        var email = $('input#signup-email').val();
        var repassword = $('input#signup-rePassword').val();

        var $numberCorrectField = 0;

        if (password.length >= 4 && password != '' && passwordReg.test(password)) {
            $('input#signup-password').css('border-color', 'green');
            $form_signup.find('a#a-signup-password').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (password.length != 0) {
                $('input#signup-password').css('border-color', 'red');
                $form_signup.find('a#a-signup-password').addClass('has-error').next('span').addClass('is-visible');
            }
        }


        if (login.length >= 3 && login != '' && loginReg.test(login)) {
            $('input#signup-login').css('border-color', 'green');
            $form_signup.find('input#signup-login').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (login.length != 0) {
                $('input#signup-login').css('border-color', 'red');
                $form_signup.find('input#signup-login').addClass('has-error').next('span').addClass('is-visible');
            }
        }


        if (name.length >= 2 && name != '' && name_surnameReg.test(name)) {
            $('input#signup-name').css('border-color', 'green');
            $form_signup.find('input#signup-name').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (name.length != 0) {
                $('input#signup-name').css('border-color', 'red');
                $form_signup.find('input#signup-name').addClass('has-error').next('span').addClass('is-visible');
            }
        }


        if (surname.length >= 2 && surname != '' && name_surnameReg.test(surname)) {
            $('input#signup-surname').css('border-color', 'green');
            $form_signup.find('input#signup-surname').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (surname.length != 0) {
                $('input#signup-surname').css('border-color', 'red');
                $form_signup.find('input#signup-surname').addClass('has-error').next('span').addClass('is-visible');
            }
        }

        if (repassword == password) {
            $('input#signup-rePassword').css('border-color', 'green');
            $form_signup.find('a#a-signup-rePassword').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            if (repassword.length != 0) {
                $('input#signup-rePassword').css('border-color', 'red');
                $form_signup.find('input#signup-rePassword').addClass('has-error').next('span').addClass('is-visible');
            }
        }

        if (email.length > 5 && email != '' && emailReg.test(email)) {
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
        // e.preventDefault();
        var name_ruReg = /[А-Я]{1}[а-я]+/;
        var name_enReg = /[A-Z]{1}[a-z]+/;
        // var loginReg = /[a-zA-Zа-яА-Я]+\w+/;
        // var passwordReg = /\w{6,}/;
        // var emailReg = /^[-a-z0-9!#$%&'*+/=?^_`{|}~]+(?:\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])$/;
        //
        var $form_modal = $('#add-form');
        var $form_add = $form_modal.find('#add-product');

        var product_type = $('select#product-type').val();
        var name_ru = $('input#name-ru').val();
        var name_en = $('input#name-en').val();
        var value = $('input#value').val();
        var cost = $('input#cost').val();
        var status = $('select#status').val();
        var descrirtion_ru = $('input#descrirtion-ru').val();
        var descrirtion_en = $('input#descrirtion-en').val();
        var image_name = $('input#image-name').val();
        var $numberCorrectField = 0;

        if (product_type != null) {
            $('select#product-type').css('border-color', 'green');
            $form_add.find('select#product-type').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#signup-login').css('border-color', 'red');
            $form_add.find('input#signup-login').addClass('has-error').next('span').addClass('is-visible');
        }

        if (product_type != null) {
            $('select#product-type').css('border-color', 'green');
            $form_add.find('select#product-type').removeClass('has-error').next('span').removeClass('is-visible');
            $numberCorrectField++;
        } else {
            $('input#signup-login').css('border-color', 'red');
            $form_add.find('input#signup-login').addClass('has-error').next('span').addClass('is-visible');
        }
    });

});