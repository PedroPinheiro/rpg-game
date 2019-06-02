package pihead.games.rpg.commandline.views.pages;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.models.GameTypeModel;
import pihead.games.rpg.commandline.responses.GameResponse;
import pihead.games.rpg.commandline.responses.GameResponseShutdown;

// TODO: incomplete class
public class GameHomePage extends Page<GameTypeModel> {

    @Override
    protected GameResponse doShow(GameTypeModel model) {

        clearScreen();

        Console.print(TextColor.RED, model.getTitleInputStream());

        Console.print(TextColor.YELLOW, "\t1. ");
        Console.println("Leon");

        Console.print(TextColor.YELLOW, "\t2. ");
        Console.println("Claire");

        Console.print(TextColor.YELLOW, "\t0. ");
        Console.println("\nGet back");

        Console.println(" ");

        readLine();
//        selectCharacter();

        return new GameResponseShutdown();
    }

    public void doShow() {


    }

//    public void show(int option) {
//
//        clearScreen();
//
//        if (option == 1) {
//            Console.printResource(TextColor.RED, Resource.RESIDENT_EVIL);
//        } else {
//            Console.printResource(TextColor.PURPLE, Resource.SILENT_HILL);
//        }
//
//        Console.print(TextColor.YELLOW, "\t1. ");
//        Console.println("Leon");
//
//        Console.print(TextColor.YELLOW, "\t2. ");
//        Console.println("Claire");
//
//        Console.print(TextColor.YELLOW, "\t0. ");
//        Console.println("\nGet back");
//
//        Console.println(" ");
//        selectCharacter();

//    }

//    private void selectCharacter() {
//
//        List<Integer> validOptions = new ArrayList<>();
//        validOptions.add(1);
//        validOptions.add(2);
//        validOptions.add(0);
//
//        Integer option = readOption("Character number", validOptions);
//
//        // TODO: This get back must return the type of the page to load
//        if (option == 0) {
//            (ApplicationContext.get(InitialPage.class)).show();
//        }
//    }
}