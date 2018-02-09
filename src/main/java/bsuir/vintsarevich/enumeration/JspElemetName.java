package bsuir.vintsarevich.enumeration;

public enum JspElemetName {
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
    REVIEW_STARS("review_stars"),
    ORDER_ID("order_id"),
    STAFF_LOGIN("staff_login"),
    STAFF_PASSWORD("staff_password"),
    OLD_PASSWORD("old_password"),
    NEW_PASSWORD("new_password"),
    NEW_PASSWORD_REPEAT("new_password_repeat"),
    POINT("point");

    private String value;

    JspElemetName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
