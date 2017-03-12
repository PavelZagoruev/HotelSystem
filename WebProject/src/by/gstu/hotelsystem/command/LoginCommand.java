package by.gstu.hotelsystem.command;

import by.gstu.hotelsystem.database.DAO.HotelDAO;
import by.gstu.hotelsystem.enumeration.ClientTypeEnum;
import by.gstu.hotelsystem.database.DAO.AccountDAO;
import by.gstu.hotelsystem.database.DAO.factory.DAOFactory;
import by.gstu.hotelsystem.helper.LoginHelper;
import by.gstu.hotelsystem.models.Hotel;
import by.gstu.hotelsystem.util.MappingUtility;
import by.gstu.hotelsystem.util.MessageUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Pavel on 16.01.2017.
 */
public class LoginCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    /**
     * URL, gets from mapping.properties
     */
    private static final String MAIN_CLIENT_PAGE_PATH = "path.page.main.client";
    private static final String MAIN_ADMINISTRATOR_PAGE_PATH = "path.page.main.administrator";
    private static final String LOGIN_PAGE = "path.page.login";

    /**
     * Params from jsp page
     */
    private static final String PARAM_NAME_USER = "user";
    private static final String PARAM_NAME_LOGIN_ERROR = "login_error";
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_HOTEL = "hotels";
    /**
     * Messages, which will be print on jsp
     */
    private static final String LOGIN_ERROR = "message.error.login.not.found";
    private static final String MESSAGE_NO_HOTELS = "message.no.hotels";
    private static final String PARAM_NAME_CLIENTS = "clients";


    private DAOFactory mysql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private AccountDAO accountDAO = mysql.getAccountDAO();
    private DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private HotelDAO hotelDao = mySql.getHotelDAO();
    private AccountDAO clientAccounts = mySql.getAccountDAO();
    /**
     * If it's driver-account, redirect to page main driver page, else redirect to main dispatcher page.
     * Create session for this account.
     *
     * @param request
     * @return page - if account exist redirect to page, else prints error message.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        if (LoginHelper.getInstance().checkAccount(login, pass)) {
            request.getSession().setAttribute(PARAM_NAME_USER, login);

            if (accountDAO.findAccountByName(login).getIsAdministrator()) {
                page = MappingUtility.getPath(MAIN_ADMINISTRATOR_PAGE_PATH);
                request.getSession().setAttribute("clientType", ClientTypeEnum.ADMINISTRATOR);
                showClients(request);
            } else {
                page = MappingUtility.getPath(MAIN_CLIENT_PAGE_PATH);
                request.getSession().setAttribute("clientType", ClientTypeEnum.CLIENT);
                    showHotels(request);
            }
            logger.info("User " + login + " log in.");
        } else {
            logger.warn("User isn't logged in.");
            page = MappingUtility.getPath(LOGIN_PAGE);
            request.setAttribute(PARAM_NAME_LOGIN_ERROR, MessageUtility.getMessage(LOGIN_ERROR));
        }

        return page;
    }
    private String getMessage(String message) {
        return MessageUtility.getMessage(message);
    }

    private void showHotels(HttpServletRequest request)
    {
        List<Hotel> hotels = hotelDao.findAll();
        if (hotels.size() > 0) {

            request.setAttribute(PARAM_NAME_HOTEL, hotels);
        } else request.setAttribute(MESSAGE_NO_HOTELS, getMessage(MESSAGE_NO_HOTELS));
    }
    private void showClients(HttpServletRequest request)
    {
        request.setAttribute(PARAM_NAME_CLIENTS, clientAccounts.getAllClientsAccount());
    }
}
