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
 * class DeleteClient created to delete clients
 */
public class DeleteClient implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(AddProduct.class);
    private JspPageName jspPageName = JspPageName.CLIENTS;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start delete client");
        try {
            IClientService clientService = ServiceFactory.getInstance().getClientService();
            Integer clientId = Integer.valueOf(request.getParameter(AttributeParameterName.CLIENT_ID.getValue()));
            clientService.deleteClient(clientId);
            response.sendRedirect(RedirectingCommandName.EDIT_CLIENTS.getCommand());
        } catch (ServiceException | IOException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.INFO, "Finish delete client");
        return jspPageName.getPath();
    }
}
