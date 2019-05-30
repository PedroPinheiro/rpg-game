package pihead.games.rpg.commandline.pages;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.resources.ResourceReader.Resource;

import java.util.ArrayList;
import java.util.List;

// TODO: incomplete class
public class GameHomePage extends BasePage {

    public void doShow() {

        printResource(Resource.RESIDENT_EVIL);

        Console.print(TextColor.YELLOW, "\t1. ");
        Console.println("Leon");

        Console.print(TextColor.YELLOW, "\t2. ");
        Console.println("Claire");

        Console.println(" ");
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
