package by.gstu.hotelsystem.database.DAO;

import by.gstu.hotelsystem.models.Apartment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 * Created by Pavel on 16.01.2017.
 */
public interface ApartmentDAO {
    Connection getConnection() throws SQLException;

    List<Apartment> findAll();

    Apartment findApartmentById(int id);

    Apartment findApartmentByClass(String className);

    boolean delete(int id);

    boolean delete(Apartment apart);

    boolean create(Apartment apart);

    Apartment update(Apartment apart);
}
