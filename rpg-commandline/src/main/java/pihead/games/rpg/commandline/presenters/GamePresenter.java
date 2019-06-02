package pihead.games.rpg.commandline.presenters;

import pihead.games.rpg.commandline.models.GameTypeModel;
import pihead.games.rpg.commandline.pages.GameHomePage;

public class GamePresenter implements Presenter<GameHomePage, GameTypeModel> {

    private GameHomePage gameHomePage;

    public GamePresenter(GameHomePage gameHomePage) {
        this.gameHomePage = gameHomePage;
    }

    @Override
    public GameHomePage getPage() {
        return gameHomePage;
    }

    @Override
    public GameTypeModel getModel() {
        return null;
    }
}
