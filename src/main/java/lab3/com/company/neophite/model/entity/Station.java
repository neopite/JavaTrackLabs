package lab3.com.company.neophite.model.entity;

public class Station {
    private long id;
    private String name;
    private boolean isActive;

    public Station() {
    }

    public Station( String name) {
        this.name = name;
    }

    public Station(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Station(long id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
