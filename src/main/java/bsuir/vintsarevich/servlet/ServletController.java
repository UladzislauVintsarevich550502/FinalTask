package bsuir.vintsarevich.servlet;

import bsuir.vintsarevich.command.CommandProvider;
import bsuir.vintsarevich.command.ICloseDBCommand;
import bsuir.vintsarevich.command.ICommand;
import bsuir.vintsarevich.command.impl.CloseDBCommand;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "Controller",
        urlPatterns = {"/cafe.by/*"},
        initParams = {@WebInitParam(name = "init_log4j", value = "/WEB-INF/log4j.properties")})
@MultipartConfig

public class ServletController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ServletController.class);
    private static final String LOG4J_PARAM = "init_log4j";
    private static final long serialVersionUID = 1L;

    private CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    public void init() {
        try {
            String prefix = getServletContext().getRealPath("");

            String filename = getInitParameter(LOG4J_PARAM);
            if (filename == null) {
                BasicConfigurator.configure();
            } else {
                PropertyConfigurator.configure(prefix + File.separator + filename);
            }
        } catch (Exception e) {
            LOGGER.log(Level.FATAL, e.getMessage());
        }
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.log(Level.INFO, request.getRequestURI());
        ICommand command = commandProvider.getCommand(request);
        String page = command.execute(request, response);
        LOGGER.log(Level.INFO, page);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        if (dispatcher != null && !response.isCommitted()) {
            dispatcher.forward(request, response);
        } else {
            errorMessageDirectlyFromResponse(request, response);
        }
    }

    @Override
    public void destroy() {
        ICloseDBCommand command = new CloseDBCommand();
        command.closeDB();
    }

    /**
     * @param request
     * @param response
     * @throws IOException
     */
    private void errorMessageDirectlyFromResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println(" UNKNOWN ERROR");
    }
}
