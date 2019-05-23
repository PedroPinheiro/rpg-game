package domain.entities;

public class Game {

    private Stage firstStage;

    public Game(Stage firstStage) {
        this.firstStage = firstStage;
    }

    public Stage getFirstStage() {
        return firstStage;
    }
}
