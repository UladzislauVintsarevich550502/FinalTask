package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.AttributeParameterName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class ChangeClientStatus created to change clients' statuses
 */
public class ChangeClientStatus implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(ChangeClientStatus.class);
    private JspPageName jspPageName = JspPageName.CLIENTS;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IClientService clientService = ServiceFactory.getInstance().getClientService();
        LOGGER.log(Level.DEBUG, "Change client status start");
        try {
            Integer clientId = Integer.valueOf(request.getParameter(AttributeParameterName.CLIENT_ID.getValue()));
            clientService.changeClientStatus(clientId);
            response.sendRedirect(RedirectingCommandName.CLIENT.getCommand());
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.DEBUG, "Change client status finish");
        return jspPageName.getPath();
    }
}