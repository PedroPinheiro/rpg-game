package pihead.games.rpg.commandline.pages.selectGame;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.models.*;
import pihead.games.rpg.commandline.responses.Intent;
import pihead.games.rpg.commandline.resources.Resource;
import pihead.games.rpg.commandline.pages.selectPlayer.SelectPlayerPage;
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

        options.add(new BackPageModel());

        SelectOptionModel selectOptionModel = new SelectOptionModel("Game number", options);

        OptionModel option = selectOptionView.show(selectOptionModel);

        return buildIntent(option);

    }

    private Intent buildIntent(OptionModel option) {

        if (option instanceof BackPageModel)
            return new Intent(Intent.Action.GO_BACK);
        else
            return new Intent(SelectPlayerPage.class, option);
    }
}
