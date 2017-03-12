package by.gstu.hotelsystem.filter;

import by.gstu.hotelsystem.enumeration.ClientTypeEnum;
import by.gstu.hotelsystem.enumeration.PermissibleLinksEnum;
import by.gstu.hotelsystem.util.MappingUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Pavel on 28.01.2017.
 */
@WebFilter(filterName = "PageRedirectFilter", urlPatterns = {"/jsp/*"}, initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp")})
public class PageRedirectFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private String indexPath;
    private static final String PARAM_USER = "user";
    private static final String PARAM_CLIENT_TYPE = "clientType";
    private static final String PATH_MAIN_CLIENT_PAGE = "path.page.main.client";
    private static final String PATH_MAIN_ADMINISTRATOR_PAGE = "path.page.main.administrator";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        HttpSession session = httpRequest.getSession(false);
        String user = (String) session.getAttribute(PARAM_USER);
        ClientTypeEnum clientType = (ClientTypeEnum) session.getAttribute(PARAM_CLIENT_TYPE);
        String page = httpRequest.getRequestURI();

        if (clientType == null) {
            clientType = ClientTypeEnum.CLIENT;
            session.setAttribute(PARAM_CLIENT_TYPE, clientType);
        }
        logger.trace(user + " with status " + clientType.name() + " try to visit page " + page);

        if (clientType == ClientTypeEnum.ADMINISTRATOR) {
            if (!isPermissible(page, httpRequest.getContextPath(), clientType)) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + MappingUtility.getPath(PATH_MAIN_ADMINISTRATOR_PAGE));
            }
        } else if (clientType == ClientTypeEnum.CLIENT) {
            if (!isPermissible(page, httpRequest.getContextPath(), clientType)) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + MappingUtility.getPath(PATH_MAIN_CLIENT_PAGE));
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        indexPath = config.getInitParameter("INDEX_PATH");
    }

    private boolean isPermissible(String link, String context, ClientTypeEnum clientType) {
        String path;
        for (PermissibleLinksEnum permissibleLink : PermissibleLinksEnum.values()) {
            path = context + permissibleLink.getPath(clientType);
            if (link.equals(path)) {
                return true;
            }
        }
        return false;
    }
}
