package by.gstu.hotelsystem.command;

import by.gstu.hotelsystem.database.DAO.ApartmentDAO;
import by.gstu.hotelsystem.database.DAO.HotelDAO;
import by.gstu.hotelsystem.database.DAO.factory.DAOFactory;
import by.gstu.hotelsystem.models.Apartment;
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
public class ShowAppartment implements Command {
    private static final Logger logger = LogManager.getLogger();


    private static final String HOTELS_PAGE_PATH = "path.page.main.apartment";
    private  static final String HOTEL_CHOICE_ID = "submit_id";
    private  static final String HOTEL_NAME_MAIN = "hotelName";
    private  static final String HOTEL_ID ="hotelId";
    private static final String PARAM_ARRAY_APARTMENTS ="apartments";


    private DAOFactory mysql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private HotelDAO hotelDAO = mysql.getHotelDAO();
    private DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private ApartmentDAO apartmentDAO = mySql.getApartmentDAO();
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        List<Apartment> apartments = new ArrayList<>();
        String hotelID=request.getParameter(HOTEL_CHOICE_ID);
        request.setAttribute(HOTEL_ID,hotelID);
        Hotel hotel=hotelDAO.findHotelById(Integer.parseInt(hotelID));
        request.setAttribute(HOTEL_NAME_MAIN,hotel.getName());

        int hotelId =Integer.parseInt(request.getParameter(HOTEL_CHOICE_ID));

        apartments = apartmentDAO.findApartmentByHotelId(hotelId);
        request.setAttribute(PARAM_ARRAY_APARTMENTS,apartments);
        page = MappingUtility.getPath(HOTELS_PAGE_PATH);
        return page;
    }
    private String getMessage(String message) {
        return MessageUtility.getMessage(message);
    }
}
