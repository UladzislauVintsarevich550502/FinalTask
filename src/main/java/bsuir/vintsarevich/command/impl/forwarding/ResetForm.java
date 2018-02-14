package bsuir.vintsarevich.command.impl.forwarding;

import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.AttributeParameterName;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.factory.service.ServiceFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * class ResetForm created to Reset password if you're forgot him
 */
public class ResetForm implements ICommand {
    private JspPageName jspPageName = JspPageName.RESET_FORM;

    /**
     * @param request
     * @param response
     * @return String
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return jspPageName.getPath();
    }
}
