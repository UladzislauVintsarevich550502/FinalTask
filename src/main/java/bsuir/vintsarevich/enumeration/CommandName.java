package bsuir.vintsarevich.enumeration;

public enum CommandName {
    SIGN_IN("all"), SIGN_UP("all"), SIGN_OUT("all"), INDEX("all"), WRONG_REQUEST("all"),
    BASKET("client"), CHANGE_LOCALE("all"), ADD_PRODUCT("admin"), ADD_PRODUCT_TO_BASKET("client"),
    REMOVE_PRODUCT_FROM_BASKET("client"),ADD_ACCOUNT("client"), EDIT_CLIENTS("admin"),
    CHANGE_CLIENT_STATUS("admin"),SET_CURRENT_PAGE("all"), SEARCH_PRODUCT("all"),FIND_BY_TYPE("all"),
    PAYMENT("client"), ADD_REVIEW("client");

    private String value;

    CommandName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
