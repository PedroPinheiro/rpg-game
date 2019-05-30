package pihead.games.rpg.commandline.pages;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.resources.Resource;

import java.util.ArrayList;
import java.util.List;

public class InitialPage extends BasePage {

    private GameHomePage gameHomePage;

    public InitialPage() {
        this.gameHomePage = new GameHomePage();
    }

    public void doShow() {

        Console.printResource(Resource.PIHEAD_GAMES);

        Console.print(TextColor.YELLOW, "\t1. ");
        Console.println("Resident Evil");

        Console.println(" ");
        selectGame();

    }

    private void selectGame() {

        List<Integer> validOptions = new ArrayList<>();
        validOptions.add(1);

        Integer option = readOption("Game number", validOptions);

        if (option == 1){
            gameHomePage.show();
        }

    }
}
