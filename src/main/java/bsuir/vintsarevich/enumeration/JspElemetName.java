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
    NAME("name"),
    VALUE("value"),
    COST("cost"),
    STATUS("status"),
    DESCRIPTION("description"),
    IMAGE("image");

    private String value;

    JspElemetName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
