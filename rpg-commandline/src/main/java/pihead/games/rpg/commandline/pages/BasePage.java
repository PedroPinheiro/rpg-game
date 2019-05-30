package pihead.games.rpg.commandline.pages;

import pihead.games.rpg.commandline.utils.ResourceReader.Resource;

import java.util.List;
import java.util.Scanner;

import static pihead.games.rpg.commandline.utils.ResourceReader.getResourceAsString;

// TODO: incomplete class
public abstract class BasePage {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

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
        System.out.println(ANSI_RED + "(Wrong option)" + ANSI_RESET);
        return readOption(label, validOptions);
    }


}
