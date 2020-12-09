package lab3.com.company.neophite.model.dao.connection;

public class DataSource {
    private String usernname;
    private String password;
    private String url;

    public DataSource(String username, String password, String url) {
        this.usernname = username;
        this.password = password;
        this.url = url;
    }

    public String getUsernname() {
        return usernname;
    }

    public void setUsernname(String usernname) {
        this.usernname = usernname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
