package by.gstu.hotelsystem.util;

import java.util.ResourceBundle;

/**
 * Created by Pavel on 15.01.2017.
 */
public class ConfigurationUtility {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("resources.database");

    private ConfigurationUtility() {
    }


    public static String getValue(String key) {
        return bundle.getString(key);
    }
}
