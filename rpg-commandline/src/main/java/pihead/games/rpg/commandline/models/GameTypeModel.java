package pihead.games.rpg.commandline.models;

import java.io.InputStream;
import java.util.List;

public class GameTypeModel implements OptionModel {

    private String id;
    private String name;
    private InputStream titleInputStream;
    private List<PlayerTypeModel> playerTypeModels;

    public GameTypeModel(String id, String name, InputStream titleInputStream, List<PlayerTypeModel> playerTypeModels) {
        this.id = id;
        this.name = name;
        this.titleInputStream = titleInputStream;
        this.playerTypeModels = playerTypeModels;
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

    public List<PlayerTypeModel> getPlayerTypeModels() {
        return playerTypeModels;
    }
}
