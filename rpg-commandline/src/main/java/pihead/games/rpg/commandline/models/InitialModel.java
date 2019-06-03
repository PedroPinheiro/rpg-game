package pihead.games.rpg.commandline.models;

import java.util.List;

public class InitialModel implements Model {

    private List<GameTypeModel> gameOptions;

    public InitialModel(List<GameTypeModel> gameOptions) {
        this.gameOptions = gameOptions;
    }

    public List<GameTypeModel> getGameOptions() {
        return gameOptions;
    }
}
