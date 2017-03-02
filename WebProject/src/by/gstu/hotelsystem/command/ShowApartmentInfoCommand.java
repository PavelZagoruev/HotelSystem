package by.gstu.hotelsystem.command;

import by.gstu.hotelsystem.database.DAO.AccountDAO;
import by.gstu.hotelsystem.database.DAO.ApartmentDAO;
import by.gstu.hotelsystem.database.DAO.factory.DAOFactory;
import by.gstu.hotelsystem.models.Apartment;
import by.gstu.hotelsystem.util.MappingUtility;
import by.gstu.hotelsystem.util.MessageUtility;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Pavel on 16.01.2017.
 */
public class ShowApartmentInfoCommand {//implements Command {

    /**
     * Params from jsp page
     */
    private static final String PARAM_NAME_CAR_NAME = "carName";
    private static final String PARAM_NAME_CAR_SPEED = "speed";
    private static final String PARAM_NAME_CAR_CAPACITY = "capacity";
    private static final String PARAM_NAME_CAR_IS_HEALTHY = "isHealthy";
    private static final String PARAM_NAME_USER = "user";

    /**
     * Messages, which will be print on jsp
     */
    private static final String MESSAGE_YES = "message.yes";
    private static final String MESSAGE_NO = "message.no";

    /**
     * URL, gets from mapping.properties
     */
//    private static final String MAIN_CAR_PAGE_PATH = "path.page.car";
//
  private DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
   private AccountDAO accountDAO = mySql.getAccountDAO();
   private ApartmentDAO carDAO = mySql.getApartmentDAO();
//
//    @Override
//    public String execute(HttpServletRequest request) {
//        String page = null;
//        String login = (String) request.getSession().getAttribute(PARAM_NAME_USER);
//
//        String carName = accountDAO.findAccountByName(login).getCarName();
//        Car car = carDAO.findCarByName(carName);
//
//        if (car != null) {
//            request.setAttribute(PARAM_NAME_CAR_NAME, car.getCarName());
//            request.setAttribute(PARAM_NAME_CAR_SPEED, car.getSpeed());
//            request.setAttribute(PARAM_NAME_CAR_CAPACITY, car.getCapacity());
//            String isHealthy = car.isHealthy() ? getMessage(MESSAGE_YES) : getMessage(MESSAGE_NO);
//            request.setAttribute(PARAM_NAME_CAR_IS_HEALTHY, isHealthy);
//        }
//        page = MappingUtility.getInstance().getPath(MAIN_CAR_PAGE_PATH);
//        //page = "/jsp/car/carInfo.jsp";
//
//        return page;
//    }
//
//    private String getMessage(String message) {
//        return MessageUtility.getInstance().getMessage(message);
  //}
}
