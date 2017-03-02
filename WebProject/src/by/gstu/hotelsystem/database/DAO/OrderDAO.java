package by.gstu.hotelsystem.database.DAO;

import by.gstu.hotelsystem.models.Order;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 * Created by Pavel on 16.01.2017.
 */
public interface OrderDAO {
    Connection getConnection() throws SQLException;

    List<Order> findAll();

    Order findOrderById(int id);

    boolean delete(Order order);

    boolean create(Order order);

    Order update(Order order);

}
