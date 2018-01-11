package com.epam.command.impl;

import com.epam.command.ICommand;
import com.epam.service.IPrescriptionService;
import com.epam.service.exception.ServiceException;
import com.epam.service.exception.ServiceLogicException;
import com.epam.service.factory.ServiceFactory;
import com.epam.service.utils.Constants;
import com.epam.servlet.RequestEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddPrescription implements ICommand {
    private static Logger logger = Logger.getLogger(AddPrescription.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INFORMATION;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String idRole = session.getAttribute(RequestEnum.USER_ROLE.getValue()).toString();
            if (idRole.equals(Constants.DOCTOR)) {
                IPrescriptionService prescriptionService = serviceFactory.getPrescriptionServiceImpl();
                String idDoctor = session.getAttribute(RequestEnum.USER_ID.getValue()).toString();
                String idUser = request.getParameter(RequestEnum.USER.getValue());
                String idMedicament = request.getParameter(RequestEnum.MEDICAMENT.getValue());
                String dateOfCompletion = request.getParameter(RequestEnum.DATE_OF_COMPLETION.getValue());
                prescriptionService.addPrescription(idDoctor, idUser, idMedicament, dateOfCompletion);

                request.setAttribute(RequestEnum.INFORMATION.getValue(), "Prescription is added");
            } else {
                jspPageName = JspPageName.INFORMATION;
                request.setAttribute(RequestEnum.INFORMATION.getValue(), "Нет прав");
            }
        } catch (ServiceException e) {
            request.setAttribute(RequestEnum.INFORMATION.getValue(), e.getCause().getMessage());
        } catch (ServiceLogicException e) {
            request.setAttribute(RequestEnum.INFORMATION.getValue(), "Wrong date of completion");
        }
        return jspPageName.getPath();
    }
}

