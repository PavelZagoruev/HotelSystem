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
   /* CREATE_TRIP {
        {
            this.command = new CreateAcommodationCommand();
       }
    },*/
    LOGOUT {
       {
            this.command = new LogoutCommand();
        }
   },
    SHOW_CLIENTS {
        {
            this.command = new ShowClientsCommand();
        }
    },
    SHOW_HOTELS{
        {
            this.command = new ShowHotelsCommand();
        }
    },

  CHANGE_LANG {
       {
          this.command = new ChangeLanguageCommand();
      }
   },
    SHOW_APARTMENT_INFO {
       {
            this.command = new ShowApartmentInfoCommand();
        }
    },
    SHOW_APARTMENT{
    {
        this.command = new ShowAppartment();
    }
},
    SHOW_ADMINISTRATOR_ORDERS {
        {
            this.command = new ShowAdministratorOrdersCommand();
        }
    },
    SHOW_CLIENT_ORDERS{
        {
            this.command = new ShowClientOrdersCommand();
        }
    },
    CREATE_ORDER {
        {
            this.command = new CreateOrderCommand();
        }
    };

    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}
