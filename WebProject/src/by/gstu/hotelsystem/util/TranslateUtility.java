package by.gstu.hotelsystem.util;

import java.util.ResourceBundle;
import java.util.Locale;

/**
 * Created by Pavel on 16.01.2017.
 */
public class TranslateUtility {
    private static final String RESOURCE_NAME = "resources.translate";
    private static ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_NAME);

    private TranslateUtility() {
    }

    public static void changeResources(Locale locale) {
        bundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    public static String getValue(String key) {
        return bundle.getString(key);
    }
}
