package bsuir.vintsarevich.command.impl;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.buisness.product.service.IProductService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.entity.Product;
import bsuir.vintsarevich.entity.User;
import bsuir.vintsarevich.enumeration.JspPageName;
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
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, this.getClass() + ":" + e.getMessage());
        }

        LOGGER.log(Level.INFO, "Finish edit clients");
        return jspPageName.getPath();
    }
}
