package by.gstu.hotelsystem.command;

import by.gstu.hotelsystem.database.DAO.AccountDAO;
import by.gstu.hotelsystem.database.DAO.ApartmentDAO;
import by.gstu.hotelsystem.database.DAO.factory.DAOFactory;
import by.gstu.hotelsystem.helper.LoginHelper;
import by.gstu.hotelsystem.models.Account;
import by.gstu.hotelsystem.models.Apartment;
import by.gstu.hotelsystem.util.MappingUtility;
import by.gstu.hotelsystem.util.MessageUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Pavel on 16.01.2017.
 */
public class RegistrationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Params from jsp page
     */
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_YES = "yes";
    private static final String PARAM_NAME_SUCCESS = "registrationComplete";

    /**
     * Params from jsp page, where will be print error messages
     */
    private static final String PARAM_NAME_ERROR_LOGIN = "loginError";
    private static final String PARAM_NAME_ERROR_PASSWORD = "passwordError";
    private static final String PARAM_NAME_ERROR_CAR_NAME = "carNameError";
    private static final String PARAM_NAME_ERROR_CAR_SPEED = "carSpeedError";
    private static final String PARAM_NAME_ERROR_CAR_CAPACITY = "carCapasityError";
    private static final String PARAM_NAME_ERROR_ACCOUNT_EXIST = "accountExist";

    /**
     * URL, gets from mapping.properties
     */
    private static final String LOGIN_PAGE = "path.page.login";
    private static final String REGISTRATION_PAGE = "path.page.registration";

    /**
     * Messages, which will be print on jsp
     */
    private static final String LOGIN_ERROR = "message.error.login";
    private static final String PASSWORD_ERROR = "message.error.password";
    private static final String CAR_NAME_ERROR = "message.error.carName";
    private static final String CAR_SPEED_ERROR = "message.error.carSpeed";
    private static final String CAR_CAPACITY_ERROR = "message.error.carCapacity";
    private static final String ERROR_ACCOUNT_EXIST = "message.error.accountExist";
    private static final String SUCCESS_REGISTRATION = "registration.complete";

    private DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private AccountDAO accountDAO = mySql.getAccountDAO();

    /**
     * Registration new user
     *
     * @param request
     * @return page - if registration is complete, redirect to login page, else prints error message.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);

        page = MappingUtility.getPath(REGISTRATION_PAGE);
        if (LoginHelper.getInstance().checkLogin(login)) {
            request.setAttribute(PARAM_NAME_ERROR_ACCOUNT_EXIST, getMessage(ERROR_ACCOUNT_EXIST));
            return page;
        }
        if (login.isEmpty()) {
            request.setAttribute(PARAM_NAME_ERROR_LOGIN, getMessage(LOGIN_ERROR));
        } else if (pass.isEmpty()) {
            request.setAttribute(PARAM_NAME_ERROR_PASSWORD, getMessage(PASSWORD_ERROR));
        }  else {
            createAccount(login, pass,  false);
            try {

                logger.info("New user is registered " + login);
                request.setAttribute(PARAM_NAME_SUCCESS, getMessage(SUCCESS_REGISTRATION));
            } catch (NumberFormatException e) {
                logger.error("User isn't registered.");
                //logger.error(e.getMessage());

            }
        }
        return page;
    }

    private String getMessage(String message) {
        return MessageUtility.getMessage(message);
    }

    private void createAccount(String login, String password, boolean isDispatcher) {
        Account account = new Account();
        account.setIsAdministrator(isDispatcher);
        account.setLogin(login);
        account.setPassword(password);
        accountDAO.create(account);
    }

//    private void createCar(String carName, float carSpeed, float carCapacity, String isHealthy) {
//        Car car = new Car();
//        car.setCarName(carName);
//        car.setSpeed(carSpeed);
//        car.setCapacity(carCapacity);
//        if (isHealthy.equals(PARAM_NAME_YES)) {
//            car.setIsHealthy(true);
//        } else {
//            car.setIsHealthy(false);
//        }
//        if (carDAO.create(car))
//            logger.debug("Car " + car + "was add to database.");
//    }
}
