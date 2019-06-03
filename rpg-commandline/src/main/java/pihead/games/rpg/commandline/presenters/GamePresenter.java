package pihead.games.rpg.commandline.presenters;

import pihead.games.rpg.commandline.context.ConsoleGameLoader;
import pihead.games.rpg.commandline.models.GameTypeModel;
import pihead.games.rpg.commandline.models.Model;
import pihead.games.rpg.commandline.models.OptionModel;
import pihead.games.rpg.commandline.models.PlayerTypeModel;
import pihead.games.rpg.commandline.pages.SelectPlayerPage;
import pihead.games.rpg.console.engine.RpgConsoleGame;
import pihead.games.rpg.engine.domain.ListPlayerTypes;

import java.util.List;
import java.util.stream.Collectors;

public class GamePresenter implements Presenter<SelectPlayerPage, GameTypeModel> {

    private ConsoleGameLoader consoleGameLoader;
    private SelectPlayerPage selectPlayerPage;
    private ListPlayerTypes listPlayerTypes;

    public GamePresenter(ConsoleGameLoader consoleGameLoader,
                         SelectPlayerPage selectPlayerPage,
                         ListPlayerTypes listPlayerTypes) {
        this.consoleGameLoader = consoleGameLoader;
        this.selectPlayerPage = selectPlayerPage;
        this.listPlayerTypes = listPlayerTypes;
    }

    public SelectPlayerPage getPage() {
        return selectPlayerPage;
    }

    @Override
    public GameTypeModel getModel(Model requestModel) {

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
