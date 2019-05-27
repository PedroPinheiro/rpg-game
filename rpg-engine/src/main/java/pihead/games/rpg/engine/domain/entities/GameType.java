package pihead.games.rpg.engine.domain.entities;

import pihead.games.rpg.engine.domain.entities.characters.PlayerType;

import java.util.List;

public class GameType {

    private Integer id;
    private String name;
    private String description;
    private List<PlayerType> playerTypes;

    public GameType() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<PlayerType> getPlayerTypes() {
        return playerTypes;
    }
}
