package pihead.games.rpg.commandline.pages;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.models.GameTypeModel;
import pihead.games.rpg.commandline.models.InitialModel;
import pihead.games.rpg.commandline.models.OptionModel;
import pihead.games.rpg.commandline.models.SelectOptionModel;
import pihead.games.rpg.commandline.presenters.GamePresenter;
import pihead.games.rpg.commandline.responses.GameResponse;
import pihead.games.rpg.commandline.responses.GameResponsePage;
import pihead.games.rpg.commandline.resources.Resource;
import pihead.games.rpg.commandline.views.SelectOptionView;

import java.util.List;

public class SelectGamePage extends Page<InitialModel> {

    private SelectOptionView selectOptionView;

    public SelectGamePage(SelectOptionView selectOptionView) {
        this.selectOptionView = selectOptionView;
    }

    public GameResponse doShow(InitialModel model) {

        Console.print(Resource.PIHEAD_GAMES);

        List<GameTypeModel> options = model.getGameOptions();

        SelectOptionModel selectOptionModel = new SelectOptionModel<>("Game number", options);

        OptionModel optionModel = selectOptionView.show(selectOptionModel);

        return new GameResponsePage(GamePresenter.class, optionModel);

    }
}