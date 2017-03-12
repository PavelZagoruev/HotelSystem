package by.gstu.hotelsystem.enumeration;

import by.gstu.hotelsystem.util.MappingUtility;

/**
 * Permissible links, which not blocking by PageRedirectFilter.
 * Created by Pavel on 16.01.2017.
 */
public enum PermissibleLinksEnum {

    REGISTRATION_PAGE {
        public String getPath(ClientTypeEnum clientType) {
            return MappingUtility.getPath(PATH_REGISTRATION_PAGE);
        }
    },
    LOGIN_PAGE {
        public String getPath(ClientTypeEnum clientTypeEnum) { return MappingUtility.getPath(PATH_LOGIN_PAGE);}
    },
    MAIN_CLIENT_PAGE {
        public String getPath(ClientTypeEnum clientType) {
            if(clientType == ClientTypeEnum.CLIENT) {
                return MappingUtility.getPath(PATH_MAIN_CLIENT_PAGE);
            }
            return null;
        }
    },
    MAIN_APARTMENT_PAGE {
        public String getPath(ClientTypeEnum clientType) {
            if(clientType == ClientTypeEnum.CLIENT) {
                return MappingUtility.getPath(PATH_MAIN_APARTMENT_PAGE);
            }
            return null;
        }
    },
    MAIN_ADMINISTRATOR_PAGE {
        public String getPath(ClientTypeEnum clientType) {
            if(clientType == ClientTypeEnum.ADMINISTRATOR) {
                return MappingUtility.getPath(PATH_MAIN_ADMINISTRATOR_PAGE);
            }
            return null;
        }
    },
    CLIENT_ORDERS_PAGE {
        public String getPath(ClientTypeEnum clientType) {
            if (clientType == ClientTypeEnum.CLIENT) {
                return MappingUtility.getPath(PATH_CLIENT_ORDERS);
            }
            return null;
        }
    },
    ADMINISTRATOR_ORDERS_PAGE {
        public String getPath(ClientTypeEnum clientType) {
            if (clientType == ClientTypeEnum.ADMINISTRATOR) {
                return MappingUtility.getPath(PATH_ADMINISTRATOR_ORDERS);
            }
            return null;
        }
    };

    private static final String PATH_REGISTRATION_PAGE = "path.page.registration";
    private static final String PATH_LOGIN_PAGE ="path.page.login";
    private static final String PATH_MAIN_CLIENT_PAGE = "path.page.main.client";
    private static final String PATH_MAIN_ADMINISTRATOR_PAGE = "path.page.main.administrator";
    private static final String PATH_MAIN_APARTMENT_PAGE = "path.page.main.apartment";

    private static final String PATH_CLIENT_ORDERS = "path.page.client.orders";
    private static final String PATH_ADMINISTRATOR_ORDERS = "path.page.administrator.orders";

    public abstract String getPath(ClientTypeEnum clientType);
}
