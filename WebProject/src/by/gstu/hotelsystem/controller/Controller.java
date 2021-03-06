package by.gstu.hotelsystem.controller;

import by.gstu.hotelsystem.command.Command;
import by.gstu.hotelsystem.helper.RequestHelper;
import by.gstu.hotelsystem.util.MappingUtility;
import by.gstu.hotelsystem.util.MessageUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pavel on 13.01.2017.
 */
@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {

    /**
     * URL, gets from mapping.properties
     */
    private static final String LOGIN_PAGE_PATH = "path.page.login";

    /**
     * Messages, which will be print on jsp
     */
    private static final String PAGE_NOT_FOUND = "message.nullpage";

    /**
     * Params from jsp page
     */
    private static final String PARAM_NAME_NULL_PAGE = "nullPage";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        Command command = RequestHelper.defineCommand(request);
        page = command.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = MappingUtility.getPath(LOGIN_PAGE_PATH);
            request.getSession().setAttribute(PARAM_NAME_NULL_PAGE, MessageUtility.getMessage(PAGE_NOT_FOUND));
            response.sendRedirect(request.getServletContext() + page);
        }
    }
}
