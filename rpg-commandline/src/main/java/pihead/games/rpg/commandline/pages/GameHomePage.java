package pihead.games.rpg.commandline.pages;

import pihead.games.rpg.commandline.utils.ResourceReader.Resource;

import java.util.ArrayList;
import java.util.List;

// TODO: incomplete class
public class GameHomePage extends BasePage {

    public void doShow() {

        printResource(Resource.RESIDENT_EVIL);

        selectCharacter();

        readLine();

    }

    private void selectCharacter() {

        List<Integer> validOptions = new ArrayList<>();
        validOptions.add(1);
        validOptions.add(2);

        Integer option = readOption("Character number", validOptions);
        System.out.println(option);
    }
}
