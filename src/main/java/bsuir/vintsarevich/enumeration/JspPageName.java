package bsuir.vintsarevich.enumeration;

public enum JspPageName {
    INDEX("/front/jsp/index.jsp"),
    ENTRY("/front/jsp/entry.jsp"),
    INFORMATION("/pages/common/information.jsp"),
    TEST("/front/jsp/test.jsp");


    private String path;

    JspPageName(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
