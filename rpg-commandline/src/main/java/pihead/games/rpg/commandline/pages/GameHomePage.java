package pihead.games.rpg.commandline.pages;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.context.ApplicationContext;
import pihead.games.rpg.commandline.data.gateways.MemoryGameTypeGateway;
import pihead.games.rpg.commandline.resources.Resource;
import pihead.games.rpg.engine.domain.DefaultListGameTypes;
import pihead.games.rpg.engine.domain.ListGameTypes;
import pihead.games.rpg.engine.gateway.ListGameTypeGateway;

import java.util.ArrayList;
import java.util.List;

// TODO: incomplete class
public class GameHomePage extends BasePage {

    public void doShow() {

        Console.printResource(TextColor.RED, Resource.RESIDENT_EVIL);

        Console.print(TextColor.YELLOW, "\t1. ");
        Console.println("Leon");

        Console.print(TextColor.YELLOW, "\t2. ");
        Console.println("Claire");

        Console.println(" ");
        selectCharacter();

        readLine();

    }

    public void showGame(int option) {

        clearScreen();

        if (option == 1) {
            Console.printResource(TextColor.RED, Resource.RESIDENT_EVIL);
        } else {
            Console.printResource(TextColor.PURPLE, Resource.SILENT_HILL);
        }

        Console.print(TextColor.YELLOW, "\t1. ");
        Console.println("Leon");

        Console.print(TextColor.YELLOW, "\t2. ");
        Console.println("Claire");

        Console.print(TextColor.YELLOW, "\t0. ");
        Console.println("\nGet back");

        Console.println(" ");
        selectCharacter();

    }

    private void selectCharacter() {

        List<Integer> validOptions = new ArrayList<>();
        validOptions.add(1);
        validOptions.add(2);
        validOptions.add(0);

        Integer option = readOption("Character number", validOptions);

        // TODO: This get back must return the type of the page to load
        if (option == 0) {
            (ApplicationContext.get(InitialPage.class)).show();
        }
    }
}
