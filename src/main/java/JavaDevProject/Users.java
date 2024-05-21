package JavaDevProject;

public class Users {
    private String log_ID ;
    private String Password ;
    private String Role ;

    public Users(){}

    public Users(String log_ID, String password, String role) {
        this.log_ID = log_ID;
        Password = password;
        Role = role;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getLog_ID() {
        return log_ID;
    }

    public void setLog_ID(String log_ID) {
        this.log_ID = log_ID;
    }
}
