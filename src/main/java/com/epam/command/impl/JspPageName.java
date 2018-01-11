package com.epam.command.impl;

public enum JspPageName {
    INDEX("pages/user/index.html"),
    MEDICAMENTS("/pages/pharmacist/index.html"),
    MEDICAMENT("/pages/common/medicament.jsp"),
    INFORMATION("/pages/common/information.jsp"),
    NEW_MEDICAMENT("/pages/pharmacist/addMedicament.jsp"),
    ADD_PRESCRIPTION("/pages/doctor/addPrescription.jsp"),
    BASKET("/pages/user/basket.jsp"),
    USERS("/pages/admin/users.jsp");


    private String path;

    JspPageName(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
