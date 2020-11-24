package lab3.com.company.neophite.model.entity;

public class Station {
    private long id;
    private String name;

    public Station() {
    }

    public Station( String name) {
        this.name = name;
    }

    public Station(long id, String name) {
        this.id = id;
        this.name = name;
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
}
