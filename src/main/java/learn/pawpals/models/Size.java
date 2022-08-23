package learn.pawpals.models;

public enum Size {

    SMALL(1, "small"),
    MEDIUM(2, "medium"),
    LARGE(3, "large");

    int id;
    String name;

    Size(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
