package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.JspElemetName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeClientStatus implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(ChangeClientStatus.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.CLIENTS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IClientService clientService = ServiceFactory.getInstance().getClientService();
        LOGGER.log(Level.DEBUG, "Change client status start");
        try {
            Integer clientId = Integer.valueOf(request.getParameter(JspElemetName.CLIENT_ID.getValue()));
            clientService.changeClientStatus(clientId);
            response.sendRedirect("/edit_clients.do");
        } catch (IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        LOGGER.log(Level.DEBUG, "Change client status finish");
        return jspPageName.getPath();
    }


}
