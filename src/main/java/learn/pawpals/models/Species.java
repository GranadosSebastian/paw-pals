package learn.pawpals.models;

public enum Species {

    cat(1, "cat"),
    dog(2, "dog"),
    rabbit(3, "rabbit"),
    hamster(4, "hamster"),
    guinea_pig(5, "guinea pig"),
    bird(6, "bird"),
    fish(7, "fish"),
    reptile(8, "reptile");

    private final String name;

    private int speciesId;

    Species(int speciesId, String name) {
        this.speciesId = speciesId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSpeciesId() {
        return speciesId;
    }


}
