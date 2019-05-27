package pihead.games.rpg.engine.domain.entities;

import pihead.games.rpg.engine.domain.entities.characters.PlayerType;

import java.util.List;

public class GameType {

    private Integer id;
    private String name;
    private String description;
    private List<PlayerType> playerTypes;

    private GameType() {

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


    // Builder

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        protected GameType instance;

        protected Builder() {
            instance = new GameType();
        }

        public Builder id(int id) {
            instance.id = id;
            return this;
        }

        public Builder name(String name) {
            instance.name = name;
            return this;
        }

        public Builder description(String description) {
            instance.name = description;
            return this;
        }

        public GameType build() {
            if (instance.id == null) {
                throw new IllegalArgumentException("Game Type Id could not be null.");
            }
            return instance;
        }
    }
}
