package pihead.games.rpg.commandline.views.pages;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.models.Model;
import pihead.games.rpg.commandline.responses.GameResponse;

import java.util.List;
import java.util.Scanner;

public abstract class Page<T extends Model> {

    protected abstract GameResponse doShow(T model);

    public GameResponse show(T model) {
        clearScreen();
        return doShow(model);
    }

    protected void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    protected String readLine() {

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Deprecated
    protected Integer readOption(String label, List<Integer> validOptions) {

        System.out.print(label + ": ");
        Scanner scanner = new Scanner(System.in);

        Integer option;

        try {
            System.out.print(TextColor.YELLOW_BOLD.getAnsi());
            option = scanner.nextInt();
            System.out.print(Console.ANSI_RESET);
            if (!validOptions.contains(option)) {
                throw new Exception();
            }
        } catch (Exception ex) {
            option = reReadOption(label, validOptions);
        }

        return option;
    }

    private Integer reReadOption(String label, List<Integer> validOptions) {
        Console.println(TextColor.RED, "(Wrong option)");
        return readOption(label, validOptions);
    }


}
