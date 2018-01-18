package bsuir.vintsarevich.enumeration;

public enum JspElemetName {
    SIGNINLOGIN("signin-login"),
    SIGNINPASSWORD("signin-password"),
    LOGIN("login"),
    PASSWORD("password"),
    SURNAME("surname"),
    NAME("name"),
    ADDRESS("address"),
    EMAIL("email"),
    J_SESSION_ID("jsessionid"),
    USER_LOGIN("user_login"),
    USER_ROLE("user_role"),
    USER_ID("user_id"),
    INFORMATION("information");

    private String value;

    JspElemetName(String path) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
