package learn.pawpals.models;

public enum Species {

    CAT(1, "cat"),
    DOG(2, "dog"),
    RABBIT(3, "rabbit"),
    HAMSTER(4, "hamster"),
    GUINEA_PIG(5, "guinea pig"),
    BIRD(6, "bird"),
    FISH(7, "fish"),
    REPTILE(8, "reptile");

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
