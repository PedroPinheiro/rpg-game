package pihead.games.rpg.commandline.models;

public class OptionModel {

    private int id;
    private String name;

    public OptionModel(int id, String name) {
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
