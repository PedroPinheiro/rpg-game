package domain.entities;

import domain.entities.characters.PlayerType;

import java.util.List;

public class Game {

    private Stage firstStage;
    private Stage lastStage;
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
}
