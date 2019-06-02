package pihead.games.rpg.commandline.presenters;

import pihead.games.rpg.commandline.models.GameTypeModel;
import pihead.games.rpg.commandline.models.Model;
import pihead.games.rpg.commandline.views.pages.GameHomePage;

public class GamePresenter implements Presenter<GameHomePage, GameTypeModel> {

    private GameHomePage gameHomePage;

    public GamePresenter(GameHomePage gameHomePage) {
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

        return (GameTypeModel) requestModel;

    }

}
