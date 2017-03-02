package by.gstu.hotelsystem.command;

import by.gstu.hotelsystem.util.MappingUtility;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Pavel on 16.01.2017.
 */
public class EmptyCommand implements Command {

    /**
     * URL, gets from mapping.properties
     */
    private static final String LOGIN_PAGE_PATH = "path.page.login";

    /**
     * Executes when command is empty
     *
     * @param request
     * @return page - redirect to login page
     */
    @Override
    public String execute(HttpServletRequest request) {
        return MappingUtility.getPath(LOGIN_PAGE_PATH);
    }
}
