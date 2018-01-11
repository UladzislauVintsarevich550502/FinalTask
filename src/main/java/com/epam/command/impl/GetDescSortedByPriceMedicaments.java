package com.epam.command.impl;

import com.epam.command.ICommand;
import com.epam.entity.Medicament;
import com.epam.service.IMedicamentService;
import com.epam.service.exception.ServiceException;
import com.epam.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetDescSortedByPriceMedicaments implements ICommand {
    private static Logger logger = Logger.getLogger(GetDescSortedByPriceMedicaments.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.MEDICAMENTS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            IMedicamentService iMedicamentService = serviceFactory.getMedicamentServiceImpl();
            List<Medicament> medicaments = iMedicamentService.getDescSortedByPriceMedicaments();
            logger.info(request.getHeader("User-Agent") + " successfully getting desc sorted by price meds ");
            request.setAttribute("medicaments", medicaments);
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return jspPageName.getPath();
    }
}
