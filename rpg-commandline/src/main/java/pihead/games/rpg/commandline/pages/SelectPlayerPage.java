package pihead.games.rpg.commandline.pages;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.models.GameTypeModel;
import pihead.games.rpg.commandline.models.OptionModel;
import pihead.games.rpg.commandline.models.PlayerTypeModel;
import pihead.games.rpg.commandline.models.SelectOptionModel;
import pihead.games.rpg.commandline.presenters.PlayerNamePresenter;
import pihead.games.rpg.commandline.responses.GameResponse;
import pihead.games.rpg.commandline.responses.GameResponsePage;
import pihead.games.rpg.commandline.views.SelectOptionView;

import java.util.List;

// TODO: incomplete class
public class SelectPlayerPage extends Page<GameTypeModel> {

    private SelectOptionView selectOptionView;

    public SelectPlayerPage(SelectOptionView selectOptionView) {
        this.selectOptionView = selectOptionView;
    }

    @Override
    protected GameResponse doShow(GameTypeModel model) {

        clearScreen();

        Console.print(TextColor.RED, model.getTitleInputStream());

        List<PlayerTypeModel> options = model.getPlayerTypeModels();

        SelectOptionModel selectOptionModel = new SelectOptionModel<>("Player number", options);

        OptionModel optionModel = selectOptionView.show(selectOptionModel);

        Console.print("Your name: ");
        Console.print("Your name: ");
        String playerName = readLine();


        readLine();

        return new GameResponsePage(PlayerNamePresenter.class, optionModel);

    }

}
