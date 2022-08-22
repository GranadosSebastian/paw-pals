package learn.pawpals.models;

public enum Species {

    CAT(1, "Cat"),
    DOG(2, "Dog"),
    RABBIT(3, "Rabbit"),
    HAMSTER(4, "Hamster"),
    GUINEA_PIG(5, "Guinea Pig"),
    BIRD(6, "Bird"),
    FISH(7, "Fish"),
    LIZARD(8, "Lizard"),
    SNAKE(9, "Snake");

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
