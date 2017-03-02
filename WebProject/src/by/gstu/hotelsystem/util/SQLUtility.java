package by.gstu.hotelsystem.util;

import java.util.ResourceBundle;

/**
 * Created by Pavel on 16.01.2017.
 */
public  class SQLUtility {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("resources.sql");

    private SQLUtility() {
    }
    public static String getQuery(String query) {
        return bundle.getString(query);
    }
}
