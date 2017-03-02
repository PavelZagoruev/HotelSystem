package by.gstu.hotelsystem.database.DAO;

import by.gstu.hotelsystem.models.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 * Created by Pavel on 16.01.2017.
 */
public interface AccountDAO {
    Connection getConnection() throws SQLException;

    List<Account> findAll();

    Account findAccountById(int id);

    Account findAccountByName(String login);

    boolean delete(int id);

    boolean delete(Account account);

    boolean create(Account account);

    Account update(Account account);

    List<Account> getAllClientsAccount();

    Account findAccountByClient(String login);
}
