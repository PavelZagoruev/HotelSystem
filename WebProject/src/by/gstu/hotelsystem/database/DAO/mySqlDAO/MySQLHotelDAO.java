package by.gstu.hotelsystem.database.DAO.mySqlDAO;

import by.gstu.hotelsystem.database.DAO.AccountDAO;
import by.gstu.hotelsystem.database.ConnectorDB;
import by.gstu.hotelsystem.database.DAO.HotelDAO;
import by.gstu.hotelsystem.models.Account;
import by.gstu.hotelsystem.models.Apartment;
import by.gstu.hotelsystem.models.Hotel;
import by.gstu.hotelsystem.util.CloseUtility;
import by.gstu.hotelsystem.util.SQLUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavel on 16.01.2017.
 */
public class MySQLHotelDAO implements HotelDAO{
    private static final Logger logger = LogManager.getLogger();
    /**
     * SQL queries, gets from sql.properties
     */
    private static final String SQL_SELECT_ALL_HOTELS = "SELECT_ALL_HOTELS";
    private static final String SQL_SELECT_HOTEL_BY_ID = "SELECT_HOTEL_BY_ID";
    private static final String SQL_SELECT_HOTEL_BY_NAME = "SELECT_HOTEL_BY_NAME";

    private static final String SQL_DELETE_HOTEL_BY_ID = "DELETE_HOTEL_BY_ID";
    private static final String SQL_INSERT_HOTEL = "INSERT_HOTEL";
    private static final String SQL_UPDATE_HOTEL = "UPDATE_HOTEL";

    /**
     * Columns name
     */
    private static final String HOTEL_ID = "hotelID";
    private static final String HOTEL_NAME = "hotelName";

    public Connection getConnection() throws SQLException
    {
        Connection connection;
        connection= ConnectorDB.getInstance().getConnection();
        if(connection == null)
            throw new SQLException("Connection is null!");
        return connection;
    }

    public List<Hotel> findAll()
    {
        List<Hotel> hotels =new ArrayList<>();
        Hotel hotel = null;
        PreparedStatement st = null;
        ResultSet rs=null;
        Connection connection=null;
        try
        {
            connection=getConnection();
            st=connection.prepareStatement(SQLUtility.getQuery(SQL_SELECT_ALL_HOTELS),Statement.RETURN_GENERATED_KEYS);
            rs=st.executeQuery();
            while (rs.next()) {
                hotel = new Hotel();
                hotel.setId(rs.getInt(HOTEL_ID));
                hotel.setName(rs.getString(HOTEL_NAME));

                hotels.add(hotel);
            }
        }catch (SQLException e) {
            logger.error("Error while finding all accounts.");
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.close(st);
            if (rs != null)
                CloseUtility.close(rs);
            if (connection != null)
                CloseUtility.close(connection);
        }
        return hotels;

    }
    public Hotel findHotelById(int id){
        PreparedStatement st = null;
        Hotel hotel = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getQuery(SQL_SELECT_HOTEL_BY_ID));
            st.setLong(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                hotel = new Hotel();
                hotel.setId(rs.getInt(HOTEL_ID));
                hotel.setName(rs.getString(HOTEL_NAME));
            }
            if (hotel == null) {
                logger.warn("Can't find record with id [" + id + "]!");
            }
        } catch (SQLException e) {
            logger.error("Error while finding account with id " + id);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.close(st);
            if (rs != null)
                CloseUtility.close(rs);
            if (connection != null)
                CloseUtility.close(connection);
        }
        return hotel;
    }
    public Hotel findHotelByName(String hotelName) {
        PreparedStatement st = null;
        Hotel hotel= null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getQuery(SQL_SELECT_HOTEL_BY_NAME));
            st.setString(1, hotelName);
            rs = st.executeQuery();
            while (rs.next()) {
                hotel = new Hotel();
                hotel.setId(rs.getInt(HOTEL_ID));
                hotel.setName(rs.getString(HOTEL_NAME));
            }
            if (hotel == null) {
                logger.warn("Can't find record with login [" + hotelName + "]!");
            }
        } catch (SQLException e) {
            logger.error("Error while finding account with login " + hotelName);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.close(st);
            if (rs != null)
                CloseUtility.close(rs);
            if (connection != null)
                CloseUtility.close(connection);
        }
        return hotel;
    }

    @Override
    public boolean delete(Hotel hotel) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getQuery(SQL_DELETE_HOTEL_BY_ID));
            st.setInt(1, hotel.getId());
            int result = st.executeUpdate();
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while deleting account " + hotel);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.close(st);
            if (connection != null)
                CloseUtility.close(connection);
        }
        return false;
    }

    @Override
    public boolean create(Hotel hotel) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getQuery(SQL_INSERT_HOTEL), Statement.RETURN_GENERATED_KEYS);
            st.setString(1, hotel.getName());
            int result = st.executeUpdate();
            rs = st.getGeneratedKeys();
            while (rs.next()) {
                logger.trace(rs.getInt(1));
                hotel.setId(rs.getInt(1));
            }
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while creating account " + hotel);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.close(st);
            if (rs != null)
                CloseUtility.close(rs);
            if (connection != null)
                CloseUtility.close(connection);
        }
        return false;
    }

    @Override
    public Hotel update(Hotel hotel) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getQuery(SQL_UPDATE_HOTEL));
            st.setString(1, hotel.getName());
            st.setInt(2, hotel.getId());
            st.executeUpdate();
            return hotel;
        } catch (SQLException e) {
            logger.error("Error while updating account " + hotel);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.close(st);
            if (connection != null)
                CloseUtility.close(connection);
        }
        return hotel;
    }
}
