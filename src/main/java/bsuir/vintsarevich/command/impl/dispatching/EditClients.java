package bsuir.vintsarevich.command.impl.dispatching;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditClients implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(EditClients.class);
    private JspPageName jspPageName = JspPageName.CLIENTS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Start edit clients");
        try {
            IClientService clientService = ServiceFactory.getInstance().getClientService();
            List<Client> clients = clientService.getAllClients();
            request.setAttribute("clients", clients);
            request.getSession().setAttribute("pageCommand", RedirectingCommandName.EDIT_CLIENTS.getCommand());
        } catch (ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }

        LOGGER.log(Level.INFO, "Finish edit clients");
        return jspPageName.getPath();
    }
}