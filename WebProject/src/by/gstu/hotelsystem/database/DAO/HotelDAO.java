package by.gstu.hotelsystem.database.DAO;

import by.gstu.hotelsystem.models.Hotel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 * Created by Pavel on 16.01.2017.
 */
public interface HotelDAO {
    Connection getConnection() throws SQLException;

    List<Hotel> findAll();

    Hotel findHotelById(int id);

    Hotel findHotelByName(String name);

    boolean delete(Hotel hot);

    boolean create(Hotel hot);

    Hotel update(Hotel hot);
}
