package by.gstu.hotelsystem.command;

import by.gstu.hotelsystem.database.DAO.AccountDAO;
import by.gstu.hotelsystem.database.DAO.ApartmentDAO;
import by.gstu.hotelsystem.database.DAO.factory.DAOFactory;
import by.gstu.hotelsystem.models.Apartment;
import by.gstu.hotelsystem.util.MappingUtility;
import by.gstu.hotelsystem.util.MessageUtility;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 16.01.2017.
 */
public class ShowApartmentInfoCommand implements Command {

    /**
     * Params from jsp page
     */
    private static final String PARAM_ID_APARTMENT = "submit_id";
    private static final String PARAM_HOTELNAME = "hotelName";
    private static final String PARAM_ARRAY_APARTMENTS ="apartments";
    private  static final String HOTEL_ID ="hotelId";
    /**
     * URL, gets from mapping.properties
     */
    private static final String MAIN_APARTMENT_PAGE_PATH = "path.page.main.apartment";

   private DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
   private ApartmentDAO apartmentDAO = mySql.getApartmentDAO();

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        List<Apartment> apartments = new ArrayList<>();
        int hotelId =Integer.parseInt(request.getParameter(HOTEL_ID));
        String hotelNam = request.getParameter(PARAM_HOTELNAME);
        apartments = apartmentDAO.findApartmentByHotelId(hotelId);
        request.setAttribute(PARAM_ARRAY_APARTMENTS,apartments);
        /*request.setAttribute(PARAM_HOTELNAME,hotelNam);*/
        return  page = MappingUtility.getPath(MAIN_APARTMENT_PAGE_PATH);

    }
    private String getMessage(String message) {
        return MessageUtility.getMessage(message);
  }
}
