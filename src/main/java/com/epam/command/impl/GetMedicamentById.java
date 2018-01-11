package com.epam.command.impl;

import com.epam.command.ICommand;
import com.epam.entity.Medicament;
import com.epam.service.IMedicamentService;
import com.epam.service.exception.ServiceException;
import com.epam.service.factory.ServiceFactory;
import com.epam.servlet.RequestEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetMedicamentById implements ICommand {

    private static Logger logger = Logger.getLogger(GetMedicamentById.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.MEDICAMENT;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String idMedicament = request.getParameter(RequestEnum.ID_MEDICAMENT.getValue());
        try {
            IMedicamentService iMedicamentService = serviceFactory.getMedicamentServiceImpl();
            Medicament medicament = iMedicamentService.getMedicamentById(idMedicament);
            logger.info(request.getHeader("Successfully getting medicament by id"));
            request.setAttribute("med", medicament);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return jspPageName.getPath();
    }
}
