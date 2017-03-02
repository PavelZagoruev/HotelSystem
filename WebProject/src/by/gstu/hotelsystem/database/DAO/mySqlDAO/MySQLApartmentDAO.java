package by.gstu.hotelsystem.database.DAO.mySqlDAO;

import by.gstu.hotelsystem.database.DAO.AccountDAO;
import by.gstu.hotelsystem.database.ConnectorDB;
import by.gstu.hotelsystem.database.DAO.ApartmentDAO;
import by.gstu.hotelsystem.models.Account;
import by.gstu.hotelsystem.models.Apartment;
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
public class MySQLApartmentDAO implements ApartmentDAO{
    private static final Logger logger = LogManager.getLogger();
    /**
     * SQL queries, gets from sql.properties
     */
    private static final String SQL_SELECT_ALL_APARTMETS = "SELECT_ALL_APARTMENTS";
    private static final String SQL_SELECT_APARTMENT_BY_ID = "SELECT_APARTMENT_BY_ID";
    private static final String SQL_SELECT_APARTMENTS_BY_CLASSAPARTMENT = "SELECT_APARTMENTS_BY_CLASSAPARTMENT";

    private static final String SQL_DELETE_APARTMENT_BY_ID = "DELETE_APARTMENT_BY_ID";
    private static final String SQL_INSERT_APARTMENT = "INSERT_APARTMENT";
    private static final String SQL_UPDATE_APARTMENT = "UPDATE_APARTMENT";

    /**
     * Columns name
     */
    private static final String APARTMENT_ID = "apartmentID";
    private static final String APARTMENT_BED = "apartmentBed";
    private static final String APARTMENT_CLASS = "apartmentClass";
    private static final String HOTEL_NAME = "hotelName";

public Connection getConnection() throws SQLException
{
  Connection connection;
  connection= ConnectorDB.getInstance().getConnection();
  if(connection == null)
      throw new SQLException("Connection is null!");
  return connection;
}

public List<Apartment> findAll()
{
    List<Apartment> apartments =new ArrayList<>();
    Apartment apart = null;
    PreparedStatement st = null;
    ResultSet rs=null;
    Connection connection=null;
    try
    {
        connection=getConnection();
        st=connection.prepareStatement(SQLUtility.getQuery(SQL_SELECT_ALL_APARTMETS),Statement.RETURN_GENERATED_KEYS);
        rs=st.executeQuery();
        while (rs.next()) {
            apart = new Apartment();
            apart.setId(rs.getInt(APARTMENT_ID));
            apart.setClassOfApartment(rs.getString(APARTMENT_BED));
            apart.setNumberOfBed(rs.getInt(APARTMENT_BED));
            apart.setHotelName(rs.getString(HOTEL_NAME));

            apartments.add(apart);
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
    return apartments;

}
public Apartment findApartmentById(int id){
    PreparedStatement st = null;
    Apartment apart = null;
    ResultSet rs = null;
    Connection connection = null;
    try {
        connection = getConnection();
        st = connection.prepareStatement(SQLUtility.getQuery(SQL_SELECT_APARTMENT_BY_ID));
        st.setLong(1, id);
        rs = st.executeQuery();
        while (rs.next()) {
            apart = new Apartment();
            apart.setId(rs.getInt(APARTMENT_ID));
            apart.setClassOfApartment(rs.getString(APARTMENT_CLASS));
            apart.setNumberOfBed(rs.getInt(APARTMENT_BED));
            apart.setHotelName(rs.getString(HOTEL_NAME));
        }
        if (apart == null) {
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
    return apart;
}
    public Apartment findApartmentByClass(String apartmentClass) {
        PreparedStatement st = null;
        Apartment apart = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getQuery(SQL_SELECT_APARTMENTS_BY_CLASSAPARTMENT));
            st.setString(3, apartmentClass);
            rs = st.executeQuery();
            while (rs.next()) {
                apart = new Apartment();
                apart.setId(rs.getInt(APARTMENT_ID));
                apart.setClassOfApartment(rs.getString(APARTMENT_CLASS));
                apart.setNumberOfBed(rs.getInt(APARTMENT_BED));
                apart.setHotelName(rs.getString(HOTEL_NAME));
            }
            if (apart == null) {
                logger.warn("Can't find record with login [" + apartmentClass + "]!");
            }
        } catch (SQLException e) {
            logger.error("Error while finding account with login " + apartmentClass);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.close(st);
            if (rs != null)
                CloseUtility.close(rs);
            if (connection != null)
                CloseUtility.close(connection);
        }
        return apart;
    }

    @Override
    public boolean delete(int id) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getQuery(SQL_DELETE_APARTMENT_BY_ID));
            st.setInt(1, id);
            int result = st.executeUpdate();
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while deleting apartment with id " + id);
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
    public boolean delete(Apartment apartment) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getQuery(SQL_DELETE_APARTMENT_BY_ID));
            st.setInt(1, apartment.getId());
            int result = st.executeUpdate();
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while deleting account " + apartment);
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
    public boolean create(Apartment apartment) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getQuery(SQL_INSERT_APARTMENT), Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, apartment.getNumberOfBed());
            st.setString(2, apartment.getClassOfApartment());
            st.setString(3, apartment.getHotelName());
            int result = st.executeUpdate();
            rs = st.getGeneratedKeys();
            while (rs.next()) {
                logger.trace(rs.getInt(1));
                apartment.setId(rs.getInt(1));
            }
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while creating account " + apartment);
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
    public Apartment update(Apartment apartment) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getQuery(SQL_UPDATE_APARTMENT));
            st.setInt(1, apartment.getNumberOfBed());
            st.setString(2, apartment.getClassOfApartment());
            st.setString(3, apartment.getHotelName());
            st.setInt(5, apartment.getId());
            st.executeUpdate();
            return apartment;
        } catch (SQLException e) {
            logger.error("Error while updating account " + apartment);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.close(st);
            if (connection != null)
                CloseUtility.close(connection);
        }
        return apartment;
    }

}
