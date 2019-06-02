package pihead.games.rpg.commandline.pages;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.models.InitialModel;
import pihead.games.rpg.commandline.models.OptionModel;
import pihead.games.rpg.commandline.presenters.GamePresenter;
import pihead.games.rpg.commandline.responses.GameResponsePage;
import pihead.games.rpg.commandline.resources.Resource;

import java.util.ArrayList;
import java.util.List;

public class InitialPage extends Page<InitialModel, GameResponsePage> {

    public GameResponsePage doShow(InitialModel model) {

        Console.printResource(Resource.PIHEAD_GAMES);

        List<OptionModel> options = model.getGameOptions();
        List<Integer> validOptions = new ArrayList<>();

        for (int i = 1; i <= options.size(); i++) {
            OptionModel option = options.get(i-1);
            Console.print(TextColor.YELLOW, "\t" + i + ". ");
            Console.println(option.getName());
            validOptions.add(i);
        }

        Console.println(" ");
        Integer integer = selectGame(validOptions);

        return new GameResponsePage(GamePresenter.class, null);

    }

    private Integer selectGame(List<Integer> validOptions) {

        return readOption("Game number", validOptions);

    }
}
