package by.gstu.hotelsystem.command;

import by.gstu.hotelsystem.database.DAO.HotelDAO;
import by.gstu.hotelsystem.database.DAO.factory.DAOFactory;
import by.gstu.hotelsystem.models.Hotel;
import by.gstu.hotelsystem.models.Order;
import by.gstu.hotelsystem.util.MappingUtility;
import by.gstu.hotelsystem.util.MessageUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Pavel on 08.03.2017.
 */



public class ShowHotelsCommand implements Command{
    private static final Logger logger = LogManager.getLogger();

    private static final String MESSAGE_NO_HOTELS = "message.no.hotels";

    private static final String HOTELS_PAGE_PATH = "path.page.main.client";

    private static final String PARAM_NAME_HOTEL = "hotels";
    private static final String PARAM_NAME_NO_ORDERS = "noHotels";

    private DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private HotelDAO hotelDao = mySql.getHotelDAO();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<Hotel> hotels = hotelDao.findAll();

        hotels = hotelDao.findAll();
        if (hotels.size() > 0) {

            request.setAttribute(PARAM_NAME_HOTEL, hotels);
        } else request.setAttribute(MESSAGE_NO_HOTELS, getMessage(MESSAGE_NO_HOTELS));


        page = MappingUtility.getPath(HOTELS_PAGE_PATH);

        return page;
    }
    private String getMessage(String message) {
        return MessageUtility.getMessage(message);
    }

}
