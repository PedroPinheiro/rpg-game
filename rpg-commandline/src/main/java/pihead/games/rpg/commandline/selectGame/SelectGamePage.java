package pihead.games.rpg.commandline.selectGame;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.models.InitialModel;
import pihead.games.rpg.commandline.models.Model;
import pihead.games.rpg.commandline.models.OptionModel;
import pihead.games.rpg.commandline.models.SelectOptionModel;
import pihead.games.rpg.commandline.responses.Intent;
import pihead.games.rpg.commandline.resources.Resource;
import pihead.games.rpg.commandline.selectPlayer.SelectPlayerPage;
import pihead.games.rpg.commandline.views.Page;
import pihead.games.rpg.commandline.views.SelectOptionView;

import java.util.List;

public class SelectGamePage implements Page {

    private SelectGamePresenter selectGamePresenter;
    private SelectOptionView selectOptionView;

    public SelectGamePage(SelectGamePresenter selectGamePresenter,
                          SelectOptionView selectOptionView) {
        this.selectGamePresenter = selectGamePresenter;
        this.selectOptionView = selectOptionView;
    }

    @Override
    public void setModel(Model model) {

    }

    public Intent show() {

        Console.print(Resource.PIHEAD_GAMES);

        InitialModel model = selectGamePresenter.getModel();

        List<OptionModel> options = model.getGameOptions();

        SelectOptionModel selectOptionModel = new SelectOptionModel("Game number", options);

        OptionModel optionModel = selectOptionView.show(selectOptionModel);

        return new Intent(SelectPlayerPage.class, optionModel);

    }
}
