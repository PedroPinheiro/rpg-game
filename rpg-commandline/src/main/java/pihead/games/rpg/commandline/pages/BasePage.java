package pihead.games.rpg.commandline.pages;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.resources.ResourceReader.Resource;
import pihead.games.rpg.commandline.console.TextColor;

import java.util.List;
import java.util.Scanner;

import static pihead.games.rpg.commandline.resources.ResourceReader.getResourceAsString;

// TODO: incomplete class
public abstract class BasePage {

    protected abstract void doShow();

    public void show() {
        clearScreen();
        doShow();
    }

    protected void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    protected void printResource(Resource resource) {

        System.out.println(getResourceAsString(resource) + "\n");
    }

    protected String readLine() {

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    protected Integer readOption(String label, List<Integer> validOptions) {

        System.out.print(label + ": ");
        Scanner scanner = new Scanner(System.in);

        Integer option;

        try {
            option = scanner.nextInt();
            if (!validOptions.contains(option)) {
                throw new Exception();
            }
        } catch (Exception ex) {
            option = reReadOption(label, validOptions);
        }

        return option;
    }

    private Integer reReadOption(String label, List<Integer> validOptions) {
        Console.printLnColor(TextColor.RED, "(Wrong option)");
        return readOption(label, validOptions);
    }


}
