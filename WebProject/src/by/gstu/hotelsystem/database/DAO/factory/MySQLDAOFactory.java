package by.gstu.hotelsystem.database.DAO.factory;

import by.gstu.hotelsystem.database.DAO.AccountDAO;
import by.gstu.hotelsystem.database.DAO.ApartmentDAO;
import by.gstu.hotelsystem.database.DAO.HotelDAO;
import by.gstu.hotelsystem.database.DAO.OrderDAO;
import by.gstu.hotelsystem.database.DAO.mySqlDAO.MySQLAccountDAO;
import by.gstu.hotelsystem.database.DAO.mySqlDAO.MySQLApartmentDAO;
import by.gstu.hotelsystem.database.DAO.mySqlDAO.MySQLHotelDAO;
import by.gstu.hotelsystem.database.DAO.mySqlDAO.MySQLOrderDAO;

/**
 * Created by Pavel on 23.01.2017.
 */
//pattern factory method fore once bd
public class MySQLDAOFactory extends DAOFactory {
    @Override
    public AccountDAO getAccountDAO() {
        return new MySQLAccountDAO();
    }

    @Override
    public ApartmentDAO getApartmentDAO() {
        return new MySQLApartmentDAO();
    }

    @Override
    public HotelDAO getHotelDAO() {
        return new MySQLHotelDAO();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new MySQLOrderDAO();
    }
}
