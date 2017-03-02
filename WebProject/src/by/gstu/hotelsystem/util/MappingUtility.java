package by.gstu.hotelsystem.util;

import java.util.ResourceBundle;

/**
 * Created by Pavel on 16.01.2017.
 */
public class MappingUtility {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("resources.mapping");

    private MappingUtility() {
    }

    public static String getPath(String key) {
        return bundle.getString(key);
    }
}
