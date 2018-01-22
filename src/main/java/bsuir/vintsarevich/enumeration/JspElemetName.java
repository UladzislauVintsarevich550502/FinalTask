package bsuir.vintsarevich.enumeration;

public enum JspElemetName {
    SIGNINLOGIN("signin_login"),
    SIGNINPASSWORD("signin_password"),
    LOGIN("login"),
    PASSWORD("password"),
    SURNAME("surname"),
    NAME("name"),
    ADDRESS("address"),
    EMAIL("email"),
    J_SESSION_ID("jsessionid"),
    USER("user"),
    INFORMATION("information");

    private String value;

    JspElemetName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
