package bsuir.vintsarevich.enums;

public enum JspPageName {
    INDEX("index.jsp"),
    ERROR("jsp/error/error.jsp"),
    CLIENT("jsp/user.jsp"),
    ADMIN("jsp/admin.jsp");

    private String path;

    JspPageName(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
