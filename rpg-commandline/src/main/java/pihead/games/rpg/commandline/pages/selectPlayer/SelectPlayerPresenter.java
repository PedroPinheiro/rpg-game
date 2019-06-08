package pihead.games.rpg.commandline.pages.selectPlayer;

import pihead.games.rpg.commandline.context.ConsoleGameLoader;
import pihead.games.rpg.commandline.models.GameTypeModel;
import pihead.games.rpg.commandline.models.Model;
import pihead.games.rpg.commandline.models.OptionModel;
import pihead.games.rpg.commandline.models.PlayerTypeModel;
import pihead.games.rpg.console.engine.RpgConsoleGame;
import pihead.games.rpg.engine.domain.ListPlayerTypes;

import java.util.List;
import java.util.stream.Collectors;

public class SelectPlayerPresenter {

    private ConsoleGameLoader consoleGameLoader;
    private ListPlayerTypes listPlayerTypes;

    public SelectPlayerPresenter(ConsoleGameLoader consoleGameLoader,
                                 ListPlayerTypes listPlayerTypes) {
        this.consoleGameLoader = consoleGameLoader;
        this.listPlayerTypes = listPlayerTypes;
    }

    public Model getModel(Model requestModel) {

        if (!(requestModel instanceof GameTypeModel)) {
            throw new RuntimeException();
        }

        return getGameTypeModel(requestModel);

    }

    private GameTypeModel getGameTypeModel(Model requestModel) {

        GameTypeModel model = (GameTypeModel) requestModel;

        RpgConsoleGame consoleGame = consoleGameLoader.getConsoleGame(model.getId());

        List<OptionModel> playerTypes = getPlayerTypesModelList(model);

        return new GameTypeModel(model.getId(),
                model.getName(),
                consoleGame.getTitle(),
                playerTypes);
    }

    private List<OptionModel> getPlayerTypesModelList(GameTypeModel model) {
        return listPlayerTypes.list(model.getId()).getPlayerTypes().stream()
                .map(pt -> new PlayerTypeModel(pt.getId(), pt.getName(), pt.getMaxHealth()))
                .collect(Collectors.toList());
    }

}
