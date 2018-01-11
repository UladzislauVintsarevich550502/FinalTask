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
import java.util.List;

public class GetMedicamentsByName implements ICommand {
    private static Logger logger = Logger.getLogger(GetMedicamentsByProducer.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(RequestEnum.NAME.getValue());
        try {
            IMedicamentService iMedicamentService = serviceFactory.getMedicamentServiceImpl();
            List<Medicament> medicaments = iMedicamentService.getMedicamentByName(name);
            logger.info(request.getHeader("Successfully getting medicaments by name"));
            request.setAttribute("medicaments", medicaments);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return jspPageName.getPath();
    }
}
