package by.gstu.hotelsystem.util;

import by.gstu.hotelsystem.database.ConnectorDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Created by Pavel on 16.01.2017.
 */
public class CloseUtility {
    private static final Logger logger = LogManager.getLogger();

    private CloseUtility() {
    }

    public static void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                logger.error("Can't close statement!");
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("Can't close result set!");
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection cn) {
        if (cn != null) {
            try {
                cn.close();
                ConnectorDB.getInstance().freeConnection(cn);
            } catch (SQLException e) {
                logger.error("Can't close connection!");
                e.printStackTrace();
            }
        }
    }
}
