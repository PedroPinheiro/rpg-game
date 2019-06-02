package pihead.games.rpg.commandline.presenters;

import pihead.games.rpg.commandline.context.ConsoleGameLoader;
import pihead.games.rpg.commandline.models.GameTypeModel;
import pihead.games.rpg.commandline.models.Model;
import pihead.games.rpg.commandline.views.pages.GameHomePage;
import pihead.games.rpg.console.engine.RpgConsoleGame;
import pihead.games.rpg.residentevil.ResidentEvilConsoleGame;

public class GamePresenter implements Presenter<GameHomePage, GameTypeModel> {

    private ConsoleGameLoader consoleGameLoader;
    private GameHomePage gameHomePage;

    public GamePresenter(ConsoleGameLoader consoleGameLoader,
                         GameHomePage gameHomePage) {
        this.consoleGameLoader = consoleGameLoader;
        this.gameHomePage = gameHomePage;
    }

    public GameHomePage getPage() {
        return gameHomePage;
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

        return new GameTypeModel(model.getId(), model.getName(), consoleGame.getTitle());
    }

}
