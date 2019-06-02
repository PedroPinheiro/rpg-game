package pihead.games.rpg.commandline.models;

import java.io.InputStream;

public class GameTypeModel implements OptionModel {

    private int id;
    private String name;
    private InputStream titleInputStream;

    public GameTypeModel(int id, String name, InputStream titleInputStream) {
        this.id = id;
        this.name = name;
        this.titleInputStream = titleInputStream;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public InputStream getTitleInputStream() {
        return titleInputStream;
    }
}
