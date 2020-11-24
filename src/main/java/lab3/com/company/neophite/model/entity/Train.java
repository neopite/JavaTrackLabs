package lab3.com.company.neophite.model.entity;

public class Train {
    private long id;
    private String model;
    private int [][] places;

    public Train() {
    }

    public Train(String model, int[][] places) {
        this.model = model;
        this.places = places;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int[][] getPlaces() {
        return places;
    }

    public void setPlaces(int[][] places) {
        this.places = places;
    }
}
