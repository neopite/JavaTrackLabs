package lab3.com.company.neophite.model.entity;

import java.util.List;
import java.util.Objects;

public class User {
    private long id;
    private String username;
    private String passwd;
    private String name;
    private int age;
    private String email;
    private float money;
    private List<Role> roles;

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

    public User(int id, String username, String password) {
        this.id = id;
        this.username  = username;
        this.passwd = password;
    }

    public User(long id, String username, String passwd, float money) {
        this.id = id;
        this.username = username;
        this.passwd = passwd;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(passwd, user.passwd) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, passwd, roles);
    }
}
