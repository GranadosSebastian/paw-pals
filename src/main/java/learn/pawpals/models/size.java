package learn.pawpals.models;

public enum size {

    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large");

    private final String name;

    size(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
