package by.gstu.hotelsystem.models;
import by.gstu.hotelsystem.enumeration.ClientTypeEnum;
/**
 * Created by Pavel on 13.01.2017.
 */
public class Account extends Regulation{
    private String password;
    private String login;
    private boolean  isAdministrator;
    private String isAdmin;
    public Account(){
    }
    public Account(int id,String password,String log,String rol){
        super(id);
        this.password=password;
        login=log;
        if(rol.equalsIgnoreCase("Administrator"))
        {
            isAdmin=rol;
            setIsAdministrator(true);
        }
        else
            setIsAdministrator(false);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean getIsAdministrator() {
        return isAdministrator;
    }
    public String getIsAdmin(){return isAdmin;}
    public void setIsAdministrator(boolean role) {
        isAdministrator = role;
    }

    public void setAdministrator(String role){if(role.equalsIgnoreCase("ADMINISTRATOR")) {
        setIsAdministrator(true);
        isAdmin=role;
    }
            else setIsAdministrator(false);}

    public int getId(){return this.getId();}
}
