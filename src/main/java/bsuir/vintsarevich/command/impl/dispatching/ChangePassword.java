package bsuir.vintsarevich.command.impl.dispatching;

import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.enumeration.JspPageName;
import bsuir.vintsarevich.enumeration.RedirectingCommandName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangePassword implements ICommand {
    private JspPageName jspPageName = JspPageName.CHANGE_PASSWORD;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("pageCommand", RedirectingCommandName.CHANGE_PASSWORD.getCommand());
        return jspPageName.getPath();
    }
}
