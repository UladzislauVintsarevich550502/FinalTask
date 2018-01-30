// jQuery(document).ready(function ($) {
//
//     // Теперь отправим наше письмо с помощью AJAX
//     $('form#login-form').submit(function (e) {
//         // Запрещаем стандартное поведение для кнопки submit
//         e.preventDefault();
//         var loginReg = /[a-zA-Zа-яА-Я]+\w+/;
//         var passwordReg = /\w{6,}/;
//         var  $form_modal = $('.cd-user-modal');
//         var $form_login = $form_modal.find('#login');
//         var $numberCorrectField = 0;
//
//         var password = $('input#signin-password').val();
//         var login = $('input#signin-login').val();
//
//         $numberCorrectField = 0;
//         if (password.length > 4 && password != '' && passwordReg.test(password)) {
//             $('input#signin-password').css('border-color', 'green');
//             $form_login.find('a#a-signin-password').removeClass('has-error').next('span').removeClass('is-visible');
//             $numberCorrectField++;
//         }
//         else {
//             $('input#signin-password').css('border-color', 'red');
//             $form_login.find('a#a-signin-password').addClass('has-error').next('span').addClass('is-visible');
//             $form_login.find('input#signin-login').removeClass('has-error').next('span').removeClass('is-visible');
//         }
//
//         if (login.length > 3 && login != '' && loginReg.test(login)) {
//             $('input#signin-login').css('border-color', 'green');
//             $form_login.find('input#signin-login').removeClass('has-error').next('span').removeClass('is-visible');
//             $numberCorrectField++;
//         }
//         else {
//             $('input#signin-login').css('border-color', 'red');
//             $form_login.find('input#signin-login').addClass('has-error').next('span').addClass('is-visible');
//         }
//         if($numberCorrectField == 0)
//         $.ajax({
//             url: "/sign_up.do",
//             method: "post"
//         });
//     });
//
//     $('form#signup-form').submit(function (e) {
//         // Запрещаем стандартное поведение для кнопки submit
//         e.preventDefault();
//         var name_surnameReg = /[A-ZА-Я][a-zA-ZА-Яа-я]/;
//         var loginReg = /[a-zA-Zа-яА-Я]+\w+/;
//         var passwordReg = /\w{6,}/;
//         var emailReg = /^([a-zA-Z])/;
//         var  $form_modal = $('.cd-user-modal');
//         var $form_login = $form_modal.find('#login');
//
//         var password = $('input#signup-password').val();
//         var login = $('input#signup-login').val();
//         var name = $('input#signup-name').val();
//         var surname = $('input#signup-surname').val();
//         var email = $('input#signup-email').val();
//         var repassword = $('input#signup-rePassword').val();
//
//         if (password.length > 4 && password != '' && passwordReg.test(password)) {
//             $('input#signin-password').css('border-color', 'green');
//             $form_login.find('a#a-signin-password').removeClass('has-error').next('span').removeClass('is-visible');
//         }
//         else {
//             $('input#signin-password').css('border-color', 'red');
//             $form_login.find('a#a-signin-password').addClass('has-error').next('span').addClass('is-visible');
//             $form_login.find('input#signin-login').removeClass('has-error').next('span').removeClass('is-visible');
//         }
//
//         if (login.length > 3 && login != '' && loginReg.test(login)) {
//             $('input#signin-login').css('border-color', 'green');
//             $form_login.find('input#signin-login').removeClass('has-error').next('span').removeClass('is-visible');
//         }
//         else {
//             $('input#signin-login').css('border-color', 'red');
//             $form_login.find('input#signin-login').addClass('has-error').next('span').addClass('is-visible');
//         }
//         $.ajax({
//             url: "/sign_up.do",
//             method: "post"
//         });
//     });
//
// });