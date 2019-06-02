package pihead.games.rpg.commandline.models;

import java.io.InputStream;

public class GameTypeModel implements OptionModel {

    private String id;
    private String name;
    private InputStream titleInputStream;

    public GameTypeModel(String id, String name, InputStream titleInputStream) {
        this.id = id;
        this.name = name;
        this.titleInputStream = titleInputStream;
    }
    public GameTypeModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public InputStream getTitleInputStream() {
        return titleInputStream;
    }
}
