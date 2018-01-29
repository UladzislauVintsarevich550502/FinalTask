package bsuir.vintsarevich.enumeration;

public enum JspPageName {
    INDEX("/front/jsp/index.jsp"),
    INFORMATION("/pages/common/information.jsp"),
    TEST("/front/jsp/test.jsp"),
    BASKET("/front/jsp/basket.jsp");


    private String path;

    JspPageName(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
