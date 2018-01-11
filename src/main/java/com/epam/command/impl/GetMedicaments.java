package com.epam.command.impl;

import com.epam.entity.Medicament;
import com.epam.service.IMedicamentService;
import com.epam.service.exception.ServiceException;
import com.epam.service.factory.ServiceFactory;
import com.epam.service.utils.Constants;
import com.epam.servlet.RequestEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetMedicaments implements com.epam.command.ICommand {
    private static Logger logger = Logger.getLogger(GetMedicamentsByProducer.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.MEDICAMENTS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String idRole = session.getAttribute(RequestEnum.USER_ROLE.getValue()).toString();
            if (Constants.PHARMACIST.equals(idRole)) {
                IMedicamentService iMedicamentService = serviceFactory.getMedicamentServiceImpl();
                List<Medicament> medicaments = iMedicamentService.getAllMedicaments();
                logger.info(request.getHeader("Successfully getting medicaments by name"));
                request.setAttribute("medicaments", medicaments);
            } else {
                jspPageName = JspPageName.INFORMATION;
                request.setAttribute(RequestEnum.INFORMATION.getValue(), "Нет прав");
            }
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        }
        return jspPageName.getPath();
    }
}
