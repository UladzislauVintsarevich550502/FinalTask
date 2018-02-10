package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.AttributeName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import bsuir.vintsarevich.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeClientStatus implements ICommand {

    private static final Logger LOGGER = Logger.getLogger(ChangeClientStatus.class);
    private JspPageName jspPageName = JspPageName.CLIENTS;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        IClientService clientService = ServiceFactory.getInstance().getClientService();
        LOGGER.log(Level.DEBUG, "Change client status start");
        try {
            Integer clientId = Integer.valueOf(request.getParameter(AttributeName.CLIENT_ID.getValue()));
            if(!clientService.changeClientStatus(clientId)){
                diagnoseError(request);
            }
            response.sendRedirect(RedirectingCommandName.CLIENT.getCommand());
        } catch (IOException | ServiceException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
        }
        LOGGER.log(Level.DEBUG, "Change client status finish");
        return jspPageName.getPath();
    }

    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.setAttribute(AttributeName.CHANGE_CLIENT_STATUS_ERROR.getValue(), "Не удалось изменить статус пользователя!");
        } else {
            request.setAttribute(AttributeName.CHANGE_CLIENT_STATUS_ERROR.getValue(), "Users status wasn't changed!");
        }
    }
}
