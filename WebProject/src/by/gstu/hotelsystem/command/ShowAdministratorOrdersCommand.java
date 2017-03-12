package by.gstu.hotelsystem.command;

import by.gstu.hotelsystem.database.DAO.OrderDAO;
import by.gstu.hotelsystem.database.DAO.factory.DAOFactory;
import by.gstu.hotelsystem.models.Order;
import by.gstu.hotelsystem.util.MappingUtility;
import by.gstu.hotelsystem.util.MessageUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 16.01.2017.
 */
public class ShowAdministratorOrdersCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Params from jsp page
     */
    private static final String PARAM_NAME_NO_ORDERS = "noOrders";
    private static final String PARAM_NAME_WAITING_ORDERS = "waitingOrders";
    private static final String PARAM_NAME_CONFIRMED_ORDERS = "confirmedOrders";
    private static final String PARAM_NAME_CLOSED_ORDERS = "closedOrders";

    /**
     * URL, gets from mapping.properties
     */
    private static final String ORDERS_PAGE_PATH = "path.page.administrator.orders";
    private static final String PARAM_NAME_USER = "user";
    /**
     * Messages, which will be print on jsp
     */
    private static final String MESSAGE_NO_ORDERS = "message.no.orders";

    private DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private OrderDAO orderDAO = mySql.getOrderDAO();

    /**
     * Show orders. Orders are sorted by their status.
     *
     * @param request
     * @return - redirect to orders page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<Order> orders = orderDAO.findAll();
        ArrayList<Order> confirmedOrders = new ArrayList<>();
        ArrayList<Order> waitingOrders = new ArrayList<>();
        ArrayList<Order> closedOrders = new ArrayList<>();
        String clientName = String.valueOf(request.getSession().getAttribute(PARAM_NAME_USER));
        if (orders.size() > 0) {
            for (Order item : orders) {
                switch (item.getStatusEnum()) {
                    case CONFIRMED:
                        confirmedOrders.add(item);
                        break;
                    case WAITING:
                        waitingOrders.add(item);
                        break;
                    case CLOSED:
                        closedOrders.add(item);
                        break;
                }
            }
            request.setAttribute(PARAM_NAME_WAITING_ORDERS, waitingOrders);
            request.setAttribute(PARAM_NAME_CLOSED_ORDERS, closedOrders);
            request.setAttribute(PARAM_NAME_CONFIRMED_ORDERS, confirmedOrders);
        } else {
            request.setAttribute(PARAM_NAME_NO_ORDERS, getMessage(MESSAGE_NO_ORDERS));
        }

        page = MappingUtility.getPath(ORDERS_PAGE_PATH);

        return page;
    }

    private String getMessage(String message) {
        return MessageUtility.getMessage(message);
    }
}
