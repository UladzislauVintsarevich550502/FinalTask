jQuery(document).ready(function ($) {
    var $form_modal = $('.cd-user-modal'),
        $form_login = $form_modal.find('#cd-login'),
        $form_signup = $form_modal.find('#cd-signup'),
        $form_forgot_password = $form_modal.find('#cd-reset-password'),
        $form_modal_tab = $('.cd-switcher'),
        $tab_login = $form_modal_tab.children('li').eq(0).children('a'),
        $tab_signup = $form_modal_tab.children('li').eq(1).children('a'),
        $forgot_password_link = $form_login.find('.cd-form-bottom-message a'),
        $back_to_login_link = $form_forgot_password.find('.cd-form-bottom-message a'),
        $main_nav = $('.main-nav');

    //открыть модальное окно
    $main_nav.on('click', function (event) {
        //показать модальный слой
        $form_modal.addClass('is-visible');
        //показать выбранную форму
        ( $(event.target).is('.cd-signup') ) ? signup_selected() : login_selected();
    });

    //закрыть модальное окно
    $('.cd-user-modal').on('click', function (event) {
        if ($(event.target).is($form_modal) || $(event.target).is('.cd-close-form')) {
            $form_modal.removeClass('is-visible');
        }
    });
    //закрыть модальное окно нажатье клавиши Esc
    $(document).keyup(function (event) {
        if (event.which == '27') {
            $form_modal.removeClass('is-visible');
        }
    });

    //переключения  вкладки от одной к другой
    $form_modal_tab.on('click', function (event) {
        event.preventDefault();
        ( $(event.target).is($tab_login) ) ? login_selected() : signup_selected();
    });

    //скрыть или показать пароль
    $('.hide-password').on('click', function () {
        var $this = $(this),
            $password_field = $this.prev('input');

        ( 'password' == $password_field.attr('type') ) ? $password_field.attr('type', 'text') : $password_field.attr('type', 'password');
        ( 'Скрыть' == $this.text() ) ? $this.text('Показать') : $this.text('Скрыть');
        //фокус и перемещение курсора в конец поля ввода
        $password_field.putCursorAtEnd();
    });

    //показать форму востановления пароля
    $forgot_password_link.on('click', function (event) {
        event.preventDefault();
        forgot_password_selected();
    });

    //Вернуться на страницу входа с формы востановления пароля
    $back_to_login_link.on('click', function (event) {
        event.preventDefault();
        login_selected();
    });

    function login_selected() {
        $form_login.addClass('is-selected');
        $form_signup.removeClass('is-selected');
        $form_forgot_password.removeClass('is-selected');
        $tab_login.addClass('selected');
        $tab_signup.removeClass('selected');
    }

    function signup_selected() {
        $form_login.removeClass('is-selected');
        $form_signup.addClass('is-selected');
        $form_forgot_password.removeClass('is-selected');
        $tab_login.removeClass('selected');
        $tab_signup.addClass('selected');
    }

    function forgot_password_selected() {
        $form_login.removeClass('is-selected');
        $form_signup.removeClass('is-selected');
        $form_forgot_password.addClass('is-selected');
    }

    //при желании можно отключить - это просто, сообщения об ошибках при заполнении
    $form_login.find('input[type="button"]').on('click', function (event) {
        event.preventDefault();
        $form_login.find('input[type="button"]').toggleClass('has-error').next('span').toggleClass('is-visible');
    });
    $form_signup.find('input[type="button"]').on('click', function (event) {
        event.preventDefault();
        $form_signup.find('input[type="email"]').toggleClass('has-error').next('span').toggleClass('is-visible');
    });

});