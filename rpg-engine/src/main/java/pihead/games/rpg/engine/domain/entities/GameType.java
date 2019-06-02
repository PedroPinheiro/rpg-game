package pihead.games.rpg.engine.domain.entities;

import pihead.games.rpg.engine.domain.entities.characters.PlayerType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameType {

    private String id;
    private String name;
    private String description;
    private List<PlayerType> playerTypes = new ArrayList<>();

    private GameType() {

    }

    public String getId() {
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

        public Builder name(String name) {
            instance.name = name;
            return this;
        }

        public Builder id(String id) {
            instance.id = id;
            return this;
        }

        public Builder description(String description) {
            instance.name = description;
            return this;
        }

        public Builder addPlayerType(PlayerType playerType) {
            instance.playerTypes.add(playerType);
            return this;
        }

        public GameType build() {
            if (instance.playerTypes.isEmpty()) {
                throw new IllegalArgumentException("Player Types available on Game Type could not be empty.");
            }
            return instance;
        }
    }
}
