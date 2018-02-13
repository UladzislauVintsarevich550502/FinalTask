package bsuir.vintsarevich.command.impl.redirecting;

import bsuir.vintsarevich.buisness.client.service.IClientService;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.entity.Client;
import bsuir.vintsarevich.enumeration.AttributeParameterName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;
import bsuir.vintsarevich.exception.service.ServiceException;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import bsuir.vintsarevich.utils.EmailSender;
import bsuir.vintsarevich.utils.SessionElements;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * class ResetPassword created to reset password
 */
public class ResetPassword implements ICommand {
    private static final Logger LOGGER = Logger.getLogger(ResetPassword.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private JspPageName jspPageName = JspPageName.INDEX;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.log(Level.INFO, "Reset password start");
        try {
            String email = request.getParameter(AttributeParameterName.RESET_EMAIL.getValue());
            IClientService clientService = serviceFactory.getClientService();
            Client client = clientService.findClientByEmail(email);
            if (client != null) {
                int code = EmailSender.generateAndSendEmail(SessionElements.getLocale(request), email);
                request.getSession().setAttribute(AttributeParameterName.CODE.getValue(), code);
                request.getSession().setAttribute(AttributeParameterName.CLIENT.getValue(), client);
                jspPageName = JspPageName.RESET_FORM;
                response.sendRedirect(RedirectingCommandName.RESET_FORM.getCommand());
            } else {
                diagnoseError(request);
                response.sendRedirect(RedirectingCommandName.INDEX.getCommand());
            }
        } catch (IOException | ServiceException | MessagingException e) {
            LOGGER.log(Level.DEBUG, this.getClass() + ":" + e.getMessage());
            jspPageName = JspPageName.ERROR;
            return jspPageName.getPath();
        }
        LOGGER.log(Level.INFO, "Reset password finish");
        return jspPageName.getPath();
    }

    /**
     * @param request
     */
    private void diagnoseError(HttpServletRequest request) {
        if (SessionElements.getLocale(request).equals("ru")) {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "Пользователя с такой почтой не найдено");
        } else {
            request.getSession().setAttribute(AttributeParameterName.HEADER_ERROR.getValue(), "User with such email wasn't found");
        }
    }
}
