package com.epam.command.impl;

import com.epam.command.ICommand;
import com.epam.servlet.RequestEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NewMedicament implements ICommand {

    private static final String PHARMACIST = "2";
    private static Logger logger = Logger.getLogger(NewMedicament.class);
    private JspPageName jspPageName = JspPageName.NEW_MEDICAMENT;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String userRole = session.getAttribute(RequestEnum.USER_ROLE.toString()).toString();
        if (!userRole.equals(PHARMACIST)) {
            jspPageName = JspPageName.INFORMATION;
        }
        return jspPageName.getPath();
    }
}
