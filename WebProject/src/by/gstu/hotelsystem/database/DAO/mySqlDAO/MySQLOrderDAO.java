package by.gstu.hotelsystem.database.DAO.mySqlDAO;

import by.gstu.hotelsystem.database.ConnectorDB;
import by.gstu.hotelsystem.database.DAO.OrderDAO;
import by.gstu.hotelsystem.models.Account;
import by.gstu.hotelsystem.models.Apartment;
import by.gstu.hotelsystem.models.Order;
import by.gstu.hotelsystem.util.CloseUtility;
import by.gstu.hotelsystem.util.SQLUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Created by Pavel on 16.01.2017.
 */
public class MySQLOrderDAO implements OrderDAO{
    private static final Logger logger = LogManager.getLogger();
    /**
     * SQL queries, gets from sql.properties
     */
    private static final String SQL_SELECT_ALL_ORDERS = "SELECT_ALL_ORDERS";
    private static final String SQL_SELECT_ORDER_BY_ID = "SELECT_ORDER_BY_ID";
    private static final String SQL_DELETE_ORDER_BY_ID = "DELETE_ORDER_BY_ID";
    private static final String SQL_INSERT_ORDER = "INSERT_ORDER";
    private static final String SQL_UPDATE_ORDER = "UPDATE_ORDER";

    /**
     * Columns name
     */
    private static final String ORDER_ID = "orderID";
    private static final String ORDER_ENTRY = "orderEntry";
    private static final String ORDER_EXIT = "orderExit";
    private static final String ACCOUNT_ID = "accountID";
    private static final String APARTMENT_ID = "apartmentID";
    private static final String ORDER_STATUS = "orderStatus";

    private MySQLAccountDAO mySQLAccountDAO = new MySQLAccountDAO();
    private MySQLApartmentDAO mySQLApartmentDAO=new MySQLApartmentDAO();
    Account account;
    Apartment apartment;

    /**
     * Gets connection from pool
     * @return connection from pool
     */
    @Override
    public Connection getConnection() throws SQLException {
        Connection connection;
        connection = ConnectorDB.getInstance().getConnection();
        if(connection == null)
            throw new SQLException("Connection is null!");
        return connection;
    }

    @Override
    public List<Order> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Order> orders = new ArrayList<>();
        Order order = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getQuery(SQL_SELECT_ALL_ORDERS));
            rs = st.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setId(rs.getInt(ORDER_ID));
                order.setOrderEntry(LocalDate.from(rs.getDate(ORDER_ENTRY).toLocalDate()));
                order.setOrderExit(LocalDate.from(rs.getDate(ORDER_EXIT).toLocalDate()));
                int accountId = Integer.parseInt(rs.getString(ACCOUNT_ID));
                account = mySQLAccountDAO.findAccountById(accountId);
                order.setOrderAccount(account);

                int apartmentId = Integer.parseInt(rs.getString(APARTMENT_ID));
                apartment = mySQLApartmentDAO.findApartmentById(apartmentId);
                order.setOrderApartment(apartment);
                order.setOrderApartmentClass(apartment.getClassOfApartment());
                order.setOrderApartmentBed(apartment.getNumberOfBed());

                order.setStatusEnum(rs.getString(ORDER_STATUS));
                orders.add(order);
            }
        } catch (IllegalArgumentException e) {
            logger.error("Incorrect status. " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("Status is null. " + e.getMessage());
        } catch (SQLException e) {
            logger.error("Error while finding all orders.");
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.close(st);
            if (rs != null)
                CloseUtility.close(rs);
            if (connection != null)
                CloseUtility.close(connection);
        }
        return orders;
    }

    @Override
    public boolean delete(Order order) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getQuery(SQL_DELETE_ORDER_BY_ID));
            st.setInt(1, order.getId());
            int result = st.executeUpdate();
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while deleting order " + order);
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
    public boolean create(Order order) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getQuery(SQL_INSERT_ORDER), Statement.RETURN_GENERATED_KEYS);
            st.setDate(1, Date.valueOf(order.getOrderEntry()));
            st.setDate(2,Date.valueOf(order.getOrderExit()));
            st.setInt(3, order.getAccountId());
            st.setInt(4, order.getApartmentId());
            st.setString(5, (order.getStatusEnum().toString()));
            int result = st.executeUpdate();
            rs = st.getGeneratedKeys();
            while (rs.next()) {
                order.setId(rs.getInt(1));
            }
            if (result != 0)
                return true;
        } catch (SQLException e) {
            logger.error("Error while creating order " + order);
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
    public Order update(Order order) {
        PreparedStatement st = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getQuery(SQL_UPDATE_ORDER));
            st.setDate(1, Date.valueOf(order.getOrderEntry()));
            st.setDate(2,Date.valueOf(order.getOrderExit()));
            st.setInt(3, order.getAccountId());
            st.setInt(4, order.getApartmentId());
            st.setInt(5, order.getId());
            st.executeUpdate();
            return order;
        } catch (SQLException e) {
            logger.error("Error while updating order " + order);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.close(st);
            if (connection != null)
                CloseUtility.close(connection);
        }
        return order;
    }

    @Override
    public Order findOrderById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Order order = null;
        Connection connection = null;
        try {
            connection = getConnection();
            st = connection.prepareStatement(SQLUtility.getQuery(SQL_SELECT_ORDER_BY_ID));
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                order = new Order();
                order.setId(rs.getInt(ORDER_ID));
                order.setOrderEntry(LocalDate.from(rs.getDate(ORDER_ENTRY).toInstant()));
                order.setOrderExit(LocalDate.from(rs.getDate(ORDER_EXIT).toInstant()));
                account= mySQLAccountDAO.findAccountById(rs.getInt(ACCOUNT_ID));
                order.setOrderAccount(account);
                apartment=mySQLApartmentDAO.findApartmentById(rs.getInt(APARTMENT_ID));
                order.setOrderApartment(apartment);
            }
            if (order == null) {
                logger.warn("Can't find record with id [" + id + "]!");
            }
            return order;
        } catch (IllegalArgumentException e) {
            logger.error("Incorrect status. " + e.getMessage());
        } catch (NullPointerException e) {
            logger.error("Status is null. " + e.getMessage());
        } catch (SQLException e) {
            logger.error("Error while finding order with id " + id);
            logger.error(e.getMessage());
        } finally {
            if (st != null)
                CloseUtility.close(st);
            if (rs != null)
                CloseUtility.close(rs);
            if (connection != null)
                CloseUtility.close(connection);
        }
        return order;
    }
}
