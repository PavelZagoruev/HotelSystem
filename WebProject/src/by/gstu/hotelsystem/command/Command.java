package by.gstu.hotelsystem.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Pavel on 16.01.2017.
 */

/**
 * Interface for commands
 */
public interface Command {
    String execute(HttpServletRequest request);
}
