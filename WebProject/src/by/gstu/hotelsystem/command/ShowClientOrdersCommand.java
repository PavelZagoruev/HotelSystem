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
 * Created by Pavel on 12.03.2017.
 */
public class ShowClientOrdersCommand implements Command {
    private static final String PARAM_NAME_NO_ORDERS = "noOrders";
    private static final String PARAM_NAME_ALL_ORDERS = "allOrders";

    /**
     * URL, gets from mapping.properties
     */
    private static final String ORDERS_PAGE_PATH = "path.page.client.orders";

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

        if (orders.size() > 0) {
            request.setAttribute(PARAM_NAME_ALL_ORDERS, orders);
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
