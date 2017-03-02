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
    MAIN_DRIVER_PAGE {
        public String getPath(ClientTypeEnum clientType) {
            if(clientType == ClientTypeEnum.CLIENT) {
                return MappingUtility.getPath(PATH_MAIN_DRIVER_PAGE);
            }
            return null;
        }
    },
    MAIN_CAR_PAGE {
        public String getPath(ClientTypeEnum clientType) {
            if(clientType == ClientTypeEnum.CLIENT) {
                return MappingUtility.getPath(PATH_MAIN_CAR_PAGE);
            }
            return null;
        }
    },
    COMPLETE_TRIP_PAGE {
        public String getPath(ClientTypeEnum clientType) {
            if(clientType == ClientTypeEnum.CLIENT) {
                return MappingUtility.getPath(PATH_COMPLETE_TRIP_PAGE);
            }
            return null;
        }
    },
    MAIN_DISPATCHER_PAGE {
        public String getPath(ClientTypeEnum clientType) {
            if(clientType == ClientTypeEnum.ADMINISTRATOR) {
                return MappingUtility.getPath(PATH_MAIN_DISPATCHER_PAGE);
            }
            return null;
        }
    },
    ORDERS_PAGE {
        public String getPath(ClientTypeEnum clientType) {
            if(clientType == ClientTypeEnum.ADMINISTRATOR) {
                return MappingUtility.getPath(ORDERS_PAGE_PATH);
            }
            return null;
        }
    },
    CREATE_TRIP_PAGE {
        public String getPath(ClientTypeEnum clientType) {
            if(clientType == ClientTypeEnum.ADMINISTRATOR) {
                return MappingUtility.getPath(PATH_CREATE_TRIP_PAGE);
            }
            return null;
        }
    };

    private static final String PATH_REGISTRATION_PAGE = "path.page.registration";
    private static final String PATH_MAIN_DRIVER_PAGE = "path.page.main.driver";
    private static final String PATH_MAIN_DISPATCHER_PAGE = "path.page.main.dispatcher";
    private static final String PATH_MAIN_CAR_PAGE = "path.page.car";
    private static final String PATH_COMPLETE_TRIP_PAGE = "path.complete.trip";
    private static final String ORDERS_PAGE_PATH = "path.page.orders";
    private static final String PATH_CREATE_TRIP_PAGE = "path.page.trip.create";

    public abstract String getPath(ClientTypeEnum clientType);
}
