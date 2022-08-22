package learn.pawpals.models;

public enum Size {

    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large");

    private final String name;

    Size(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
