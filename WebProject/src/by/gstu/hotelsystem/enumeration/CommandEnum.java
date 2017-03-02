package by.gstu.hotelsystem.enumeration;

import by.gstu.hotelsystem.command.*;

/**
 * Created by Pavel on 16.01.2017.
 */
public enum CommandEnum {

    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    EMPTY {
        {
            this.command = new EmptyCommand();
        }
    },
    REGISTRATION {
        {
            this.command=new RegistrationCommand();
        }
    },
//    SHOW_TRIPS {
//        {
//            this.command = new ShowTripsCommand();
//        }
//    },
//    CREATE_TRIP {
//        {
//            this.command = new CreateTripCommand();
//        }
//    },
//    LOGOUT {
//        {
//            this.command = new LogoutCommand();
//        }
//    },
//    SHOW_DRIVERS {
//        {
//            this.command = new ShowDriversCommand();
//        }
//    },
//    UPDATE_CAR {
//        {
//            this.command = new UpdateCarCommand();
//        }
//    },
//    COMPLETE_TRIP {
//        {
//            this.command = new CompleteTripCommand();
//        }
//    },

  CHANGE_LANG {
       {
          this.command = new ChangeLanguageCommand();
      }
   },
//    SHOW_CAR_INFO {
//        {
//            this.command = new ShowCarInfoCommand();
//        }
//    },
    SHOW_ORDERS {
        {
            this.command = new ShowOrdersCommand();
        }
    };

    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}
