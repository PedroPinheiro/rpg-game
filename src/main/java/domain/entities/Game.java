package domain.entities;

import domain.entities.characters.PlayerType;

import java.util.List;

public class Game {

    private Stage firstStage;
    private List<PlayerType> playerTypes;

    public Game(Stage firstStage, List<PlayerType> playerTypes) {
        this.firstStage = firstStage;
        this.playerTypes = playerTypes;
    }

    public Stage getFirstStage() {
        return firstStage;
    }

    public List<PlayerType> getPlayerTypes() {
        return playerTypes;
    }
}
