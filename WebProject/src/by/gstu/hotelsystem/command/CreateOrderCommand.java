package by.gstu.hotelsystem.command;

import by.gstu.hotelsystem.database.DAO.ApartmentDAO;
import by.gstu.hotelsystem.database.DAO.OrderDAO;
import by.gstu.hotelsystem.database.DAO.factory.MySQLDAOFactory;
import by.gstu.hotelsystem.models.Account;
import by.gstu.hotelsystem.models.Apartment;
import by.gstu.hotelsystem.models.Order;
import by.gstu.hotelsystem.util.MappingUtility;
import by.gstu.hotelsystem.enumeration.OrderStatusEnum;

import by.gstu.hotelsystem.database.DAO.AccountDAO;
import by.gstu.hotelsystem.database.DAO.factory.DAOFactory;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Pavel on 11.03.2017.
 */
public class CreateOrderCommand implements Command{

    private static final String MAIN_CLIENT_PAGE_PATH = "path.page.main.client";
    private static final String INPUT_ENTRY = "inputEntry";
    private static final String APARTMENT_ID = "apartmentId";
    private static final String HOTEL_ID = "hotelId";
    private static final String PARAM_NAME_USER = "user";


    private DAOFactory mysql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private OrderDAO orderDAO = mysql.getOrderDAO();
    private AccountDAO accountDAO = mysql.getAccountDAO();
    private ApartmentDAO apartmentDAO = mysql.getApartmentDAO();
    private ShowHotelsCommand showHotels = new ShowHotelsCommand();
    public String execute(HttpServletRequest request)
    {
        String page = null;
        String hotelIdi = request.getParameter(HOTEL_ID);
        String apartId = request.getParameter(APARTMENT_ID);

        String stringEntry = request.getParameter(apartId+"-1");
        String stringExit = request.getParameter(apartId+"+1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate dateEntr = LocalDate.parse(stringEntry, formatter);
        LocalDate dateExit = LocalDate.parse(stringExit,formatter);

        String userName= String .valueOf(request.getSession().getAttribute(PARAM_NAME_USER));
        Account account = accountDAO.findAccountByName(userName);

        Apartment apartment = apartmentDAO.findApartmentById(Integer.parseInt(apartId));
        Order order = new Order(dateEntr,dateExit,account,apartment,OrderStatusEnum.WAITING);
        orderDAO.create(order);

        page = showHotels.execute(request);
        return page;
    }
}
