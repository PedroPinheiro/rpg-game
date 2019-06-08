package pihead.games.rpg.commandline.pages.selectPlayer;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.models.*;
import pihead.games.rpg.commandline.pages.playerName.PlayerNamePage;
import pihead.games.rpg.commandline.responses.Intent;
import pihead.games.rpg.commandline.views.SelectOptionView;
import pihead.games.rpg.commandline.views.Page;

import java.util.List;

public class SelectPlayerPage implements Page {

    private SelectPlayerPresenter selectPlayerPresenter;
    private SelectOptionView selectOptionView;
    private GameTypeModel model;

    public SelectPlayerPage(SelectPlayerPresenter selectPlayerPresenter,
                            SelectOptionView selectOptionView) {
        this.selectPlayerPresenter = selectPlayerPresenter;
        this.selectOptionView = selectOptionView;
    }

    public void setModel(Model model) {
        if (!(model instanceof GameTypeModel)) {
            throw new RuntimeException("Model received on SelectPlayerPage is not of GameTypeModel type.");
        }
        this.model = (GameTypeModel) selectPlayerPresenter.getModel(model);
    }

    public Intent show() {

        if (model == null) {
            throw new RuntimeException("Model not set");
        }

        printHeader();
        OptionModel option = printSelectOption();

        return buildIntent(option);

    }
    private void printHeader() {

        Console.print(TextColor.RED, model.getTitleInputStream());
    }

    private OptionModel printSelectOption() {

        List<OptionModel> options = model.getPlayerTypeModels();

        options.add(new BackPageModel());

        SelectOptionModel selectOptionModel = new SelectOptionModel("Player number", options);

        return selectOptionView.show(selectOptionModel);
    }

    private Intent buildIntent(OptionModel option) {

        if (option instanceof BackPageModel)
            return new Intent(Intent.Action.GO_BACK);
        else
            return new Intent(PlayerNamePage.class, option);
    }


}
