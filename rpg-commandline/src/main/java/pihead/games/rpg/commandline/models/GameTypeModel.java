package pihead.games.rpg.commandline.models;

public class GameTypeModel implements OptionModel {

    private int id;
    private String name;

    public GameTypeModel(int id, String name) {
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
