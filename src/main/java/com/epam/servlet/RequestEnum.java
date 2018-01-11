package com.epam.servlet;

public enum RequestEnum {
    SIGN_IN("sign_in.do"),
    SIGN_UP("sign_up.do"),
    ADD_MEDICAMENT("add_medicament.do"),
    ADD_TO_BASKET("add_to_basket.do"),
    COMMAND("command.do"),
    INFORMATION("information"),
    MEDICAMENTS("medicaments"),

    MEDICAMENT("medicament"),
    INDEX("index.do"),
    LOGLOGIN("loglogin"),
    LOGPASSWORD("logpassword"),
    LOGIN("login"),
    PASSWORD("password"),
    SURNAME("surname"),
    NAME("name"),
    ADDRESS("address"),
    EMAIL("email"),
    ID_MEDICAMENT("id"),
    PRODUCER("producer"),
    PRICE("price"),
    PRESCRIPTION("prescription"),
    IMAGE("image"),
    AVAILABILITY("availability"),
    J_SESSION_ID("jsessionid"),
    USER_LOGIN("user_login"),
    USER_ROLE("user_role"),
    USER_ID("user_id"),
    USER("user"),
    DATE_OF_COMPLETION("dateOfCompletion"),
    USERS("users"),
    NUMBER("number"),
    DOSAGE("dosage");
    private String value;

    RequestEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

