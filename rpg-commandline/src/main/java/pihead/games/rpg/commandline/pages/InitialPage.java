package pihead.games.rpg.commandline.pages;

import pihead.games.rpg.commandline.resources.ResourceReader.Resource;

import java.util.ArrayList;
import java.util.List;

public class InitialPage extends BasePage {

    private GameHomePage gameHomePage;

    public InitialPage() {
        this.gameHomePage = new GameHomePage();
    }

    public void doShow() {

        printResource(Resource.PIHEAD_GAMES);

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
