package by.gstu.hotelsystem.command;

import by.gstu.hotelsystem.database.DAO.AccountDAO;
import by.gstu.hotelsystem.database.DAO.factory.DAOFactory;
import by.gstu.hotelsystem.util.MappingUtility;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Pavel on 16.01.2017.
 */
public class ShowClientsCommand  implements Command {

    /**
     * URL, gets from mapping.properties
     */
    private static final String MAIN_ADMINISTRATOR_PAGE_PATH = "path.page.main.administrator";

    /**
     * Params from jsp page
     */
    private static final String PARAM_NAME_CLIENTS = "clients";

   private DAOFactory mySql = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
   private AccountDAO clientAccounts = mySql.getAccountDAO();

    /**
     * Show all drivers to dispatcher
     *
     * @param request
     * @return page - redirect to main dispatcher page*/

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        request.setAttribute(PARAM_NAME_CLIENTS, clientAccounts.getAllClientsAccount());
       page = MappingUtility.getPath(MAIN_ADMINISTRATOR_PAGE_PATH);

        return page;
   }
}
