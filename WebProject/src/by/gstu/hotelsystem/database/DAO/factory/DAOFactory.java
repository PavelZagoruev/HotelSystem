package by.gstu.hotelsystem.database.DAO.factory;

import by.gstu.hotelsystem.database.DAO.AccountDAO;
import by.gstu.hotelsystem.database.DAO.ApartmentDAO;
import by.gstu.hotelsystem.database.DAO.HotelDAO;
import by.gstu.hotelsystem.database.DAO.OrderDAO;
/**
 * Created by Pavel on 23.01.2017.
 */
//pattern absract factory for 1 bd
public abstract class DAOFactory {
    public static final int MYSQL = 1;

    public abstract AccountDAO getAccountDAO();

    public abstract ApartmentDAO getApartmentDAO();

    public abstract HotelDAO getHotelDAO();

    public abstract OrderDAO getOrderDAO();

    public static DAOFactory getDAOFactory(int factoryNum) {
        switch (factoryNum) {
            case MYSQL:
                return new MySQLDAOFactory();
            default:
                throw new NullPointerException("No available factory.");
        }
    }
}
