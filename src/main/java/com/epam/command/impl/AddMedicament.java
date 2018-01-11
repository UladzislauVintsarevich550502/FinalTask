package com.epam.command.impl;

import com.epam.command.ICommand;
import com.epam.service.IMedicamentService;
import com.epam.service.exception.ServiceException;
import com.epam.service.exception.ServiceLogicException;
import com.epam.service.factory.ServiceFactory;
import com.epam.service.utils.Constants;
import com.epam.servlet.RequestEnum;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;

public class AddMedicament implements ICommand {

    private static Logger logger = Logger.getLogger(AddMedicament.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INFORMATION;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String idRole = session.getAttribute(RequestEnum.USER_ROLE.getValue()).toString();
            if (idRole.equals(Constants.PHARMACIST)) {
                IMedicamentService iMedicamentService = serviceFactory.getMedicamentServiceImpl();
                String name = request.getParameter(RequestEnum.NAME.getValue());
                String producer = request.getParameter(RequestEnum.PRODUCER.getValue());
                String price = request.getParameter(RequestEnum.PRICE.getValue());
                String prescription = request.getParameter(RequestEnum.PRESCRIPTION.getValue());
                Part part = request.getPart(RequestEnum.IMAGE.getValue());
                String webInfPath = request.getServletContext().getRealPath("/");
                String availability = request.getParameter(RequestEnum.AVAILABILITY.getValue());
                iMedicamentService.addMedicament(name, producer, price, prescription, part, webInfPath, availability);

                request.setAttribute(RequestEnum.INFORMATION.getValue(), "Medicament is added");
                logger.debug("\"" + name + "\" added medicament");
            } else {
                jspPageName = JspPageName.INFORMATION;
                request.setAttribute(RequestEnum.INFORMATION.getValue(), "Нет прав");
            }
        } catch (ServiceException e) {
            logger.error(((String) request.getSession().getAttribute(RequestEnum.ADD_MEDICAMENT.getValue())), e);
            request.setAttribute(RequestEnum.INFORMATION.getValue(), e.getCause().getMessage());
        } catch (ServiceLogicException e) {
            logger.error(((String) request.getSession().getAttribute(RequestEnum.ADD_MEDICAMENT.getValue())), e);
            request.setAttribute(RequestEnum.INFORMATION.getValue(), e.getCause().getMessage());
        } catch (ServletException e) {
            logger.error(((String) request.getSession().getAttribute(RequestEnum.ADD_MEDICAMENT.getValue())), e);
            request.setAttribute(RequestEnum.INFORMATION.getValue(), e.getCause().getMessage());
        } catch (IOException e) {
            logger.error(((String) request.getSession().getAttribute(RequestEnum.ADD_MEDICAMENT.getValue())), e);
            request.setAttribute(RequestEnum.INFORMATION.getValue(), e.getCause().getMessage());
        }
        return jspPageName.getPath();
    }
}
