package pihead.games.rpg.commandline.models;

import java.util.List;

public class InitialModel implements Model {

    private List<OptionModel> gameOptions;

    public InitialModel(List<OptionModel> gameOptions) {
        this.gameOptions = gameOptions;
    }

    public List<OptionModel> getGameOptions() {
        return gameOptions;
    }
}
