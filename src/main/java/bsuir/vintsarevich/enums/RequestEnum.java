package bsuir.vintsarevich.enums;

public enum RequestEnum {
    SIGN_IN("sign_in.do"),
    SIGN_UP("sign_up.do"),
    ADD_MEDICAMENT("add_medicament.do"),
    COMMAND("command.do"),
    INFORMATION("information.do"),
    MEDICAMENTS("medicaments.do"),
    MEDICAMENTS_BY_PRODUCER("medicaments_by_producer.do"),
    MEDICAMENTS_BY_NAME("medicaments_by_name.do"),
    MEDICAMENT("medicament.do"),
    INDEX("index.do");

    private String value;

    RequestEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

