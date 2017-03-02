package by.gstu.hotelsystem.helper;

import by.gstu.hotelsystem.enumeration.CommandEnum;
import by.gstu.hotelsystem.command.Command;
import by.gstu.hotelsystem.command.EmptyCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Pavel on 16.01.2017.
 */
public class RequestHelper {

    private static final String PARAM_NAME_COMMAND = "command";
    private static final Logger logger = LogManager.getLogger();

    private RequestHelper() {
    }

    /**
     * Takes action from request and return command depending from request parameter (action)
     *
     * @param request - request from controller
     * @return command
     */
    public static Command defineCommand(HttpServletRequest request) {
        Command command = new EmptyCommand();
        String action = request.getParameter(PARAM_NAME_COMMAND);
        if (action == null || action.isEmpty())
            return command;
        try {
            CommandEnum commandEnum = CommandEnum.valueOf(action.toUpperCase());
            command = commandEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            logger.error("Not found action with name: " + action);
            logger.error(e.getMessage());
        }
        return command;
    }
}
