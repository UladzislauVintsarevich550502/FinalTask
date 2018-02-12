package bsuir.vintsarevich.enumeration;

public enum AttributeName {
    SIGNIN_LOGIN("signin_login"),
    SIGNIN_PASSWORD("signin_password"),
    SIGNUP_NAME("signup_name"),
    SIGNUP_SURNAME("signup_surname"),
    SIGNUP_EMAIL("signup_email"),
    SIGNUP_LOGIN("signup_login"),
    SIGNUP_PASSWORD("signup_password"),
    USER("user"),
    J_SESSION_ID("jsessionid"),
    INFORMATION("information"),
    LOCALE("locale"),
    PRODUCT_TYPE("product_type"),
    NAME_RU("name_ru"),
    NAME_EN("name_en"),
    VALUE("value"),
    COST("cost"),
    STATUS("status"),
    DESCRIPTION_RU("description_ru"),
    DESCRIPTION_EN("description_en"),
    PRODUCT_ID("productId"),
    IMAGE("file"),
    NUMBER_FOR_DELETE("number_for_delete"),
    NUMBER_FOR_ADD("number_for_add"),
    CLIENT_ID("clientId"),
    CURRENT_PAGE("current_page"),
    SEARCH_NAME("search_name"),
    REVIEW_TEXT("review_text"),
    MARK_VALUE("mark_value"),
    ORDER_ID("order_id"),
    STAFF_LOGIN("staff_login"),
    STAFF_PASSWORD("staff_password"),
    OLD_PASSWORD("old_password"),
    NEW_PASSWORD("new_password"),
    NEW_PASSWORD_REPEAT("new_password_repeat"),
    POINT("point"),
    ADD_STAFF_ERROR("add_staff_error"),
    BASKET_ERROR("basket_error"),
    CHANGE_CLIENT_STATUS_ERROR("change_client_status_error"),
    CHANGE_PASSWORD_ERROR("change_password_error"),
    FIND_BY_TYPE_ERROR("find_by_type_error"),
    SEARCH_PRODUCT("search_product"),
    STAFF_ID("staffId"),
    ADMIN_ID("adminId"),
    REVIEW_ID("reviewId"),
    HEADER_ERROR("header_error"),
    ACCOUNT_PAYMENT_ERROR("account_payment_error");


    private String value;

    AttributeName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}