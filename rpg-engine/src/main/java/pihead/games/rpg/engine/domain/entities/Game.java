package pihead.games.rpg.engine.domain.entities;

import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.domain.entities.characters.PlayerType;

import java.util.List;

public class Game {

    private Integer id;
    private Stage firstStage;
    private Stage lastStage;
    private Player player;
    private GameType type;

    private Game() {
    }

    public Integer getId() {
        return id;
    }

    public Stage getFirstStage() {
        return firstStage;
    }

    private Player getPlayer() {
        return player;
    }

    public GameType getType() {
        return type;
    }

    public void addStage(Stage stage) {
        if (isFirstStage()) {
            firstStage = stage;
        } else {
            lastStage.next = stage;
        }
        lastStage = stage;
    }

    private boolean isFirstStage() {
        return firstStage == null;
    }


    // Builder

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        protected Game instance;

        protected Builder() {
            instance = new Game();
        }

        public Builder id(int id) {
            instance.id = id;
            return this;
        }

        public Builder player(Player player) {
            instance.player = player;
            return this;
        }

        public Builder type(GameType type) {
            instance.type = type;
            return this;
        }

        public Game build() {
            if (instance.id == null) {
                throw new IllegalArgumentException("Game Id could not be null.");
            }
            return instance;
        }
    }

}
