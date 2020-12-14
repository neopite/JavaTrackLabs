package lab3.com.company.neophite.model.entity;

public class User {
    private long id;
    private String username;
    private String passwd;
    private String name;
    private int age;
    private String email;
    private float money;

    public User(){

    }

    public User(String username, String passwd, String name, int age, String email) {
        this.username = username;
        this.passwd = passwd;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public User(long id, String username, String passwd, String name, int age, String email,float money) {
        this.id = id;
        this.username = username;
        this.passwd = passwd;
        this.name = name;
        this.age = age;
        this.email = email;
        this.money = money;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passwd='" + passwd + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
