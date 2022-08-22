package learn.pawpals.models;

public enum Species {

    CAT(1, "Cat"),
    DOG(2, "Dog"),
    RABBIT(3, "Rabbit"),
    HAMSTER(4, "Hamster"),
    GUINEA_PIG(5, "Guinea Pig"),
    FISH(6, "Fish"),
    LIZARD(7, "Lizard"),
    SNAKE(8, "Snake");

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
