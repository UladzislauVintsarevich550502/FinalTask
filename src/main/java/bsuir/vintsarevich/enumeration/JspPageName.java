package bsuir.vintsarevich.enumeration;

public enum JspPageName {
    INDEX("/front/jsp/index.jsp"),
    ERROR("/front/jsp/error.jsp"),
    TEST("/front/jsp/test.jsp"),
    BASKET("/front/jsp/client/basket.jsp"),
    CLIENTS("/front/jsp/admin/client.jsp"),
    CHANGE_PASSWORD("/front/jsp/common/change_password.jsp"),
    STAFF("/front/jsp/admin/staff.jsp"),
    ADMIN("/front/jsp/admin/admin_list.jsp");


    private String path;

    JspPageName(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}