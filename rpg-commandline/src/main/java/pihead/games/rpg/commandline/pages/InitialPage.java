package pihead.games.rpg.commandline.pages;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.resources.Resource;
import pihead.games.rpg.engine.domain.ListGameTypes;

import java.util.ArrayList;
import java.util.List;

public class InitialPage extends BasePage {

    private GameHomePage gameHomePage;
    private ListGameTypes listGameTypes;

    public InitialPage(ListGameTypes listGameTypes, GameHomePage gameHomePage) {
        this.listGameTypes = listGameTypes;
        this.gameHomePage = gameHomePage;
    }

    public void doShow() {

        Console.printResource(Resource.PIHEAD_GAMES);

        List<ListGameTypes.ResponseModel.GameTypeModel> gameTypes = listGameTypes.list().getGameTypes();
        List<Integer> validOptions = new ArrayList<>();

        for (int i = 1; i <= gameTypes.size(); i++) {
            ListGameTypes.ResponseModel.GameTypeModel gameType = gameTypes.get(i-1);
            Console.print(TextColor.YELLOW, "\t" + i + ". ");
            Console.println(gameType.getName());
            validOptions.add(i);
        }

        Console.println(" ");
        selectGame(validOptions);

    }

    private void selectGame(List<Integer> validOptions) {

        Integer option = readOption("Game number", validOptions);

        gameHomePage.showGame(option);

    }
}
