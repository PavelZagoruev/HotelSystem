package by.gstu.hotelsystem.util;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 * Created by Pavel on 16.01.2017.
 */
public class MessageUtility {
   ;
    private static final String RESOURCE_NAME = "resources.message";
    private static ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_NAME);

    public MessageUtility() {
    }

    public static void changeResources(Locale locale) {
        bundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    public static String getMessage(String key) {
        return bundle.getString(key);
    }
}
