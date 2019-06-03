package pihead.games.rpg.commandline.views;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.models.OptionModel;
import pihead.games.rpg.commandline.models.SelectOptionModel;

import java.util.*;


public class SelectOptionView<T extends OptionModel> {

    public T show(SelectOptionModel<T> model) {

        List<T> options = model.getOptions();
        Map<Integer, T> validOptions = new HashMap<>();

        for (int i = 1; i <= options.size(); i++) {
            T option = options.get(i-1);
            Console.print(TextColor.YELLOW, "\t" + i + ". ");
            Console.println(option.getName());
            validOptions.put(i, option);
        }

        Console.println(" ");

        return readOption(model.getLabel(), validOptions);

    }

    private T readOption(String label, Map<Integer, T> validOptions) {

        System.out.print(label + ": ");
        Scanner scanner = new Scanner(System.in);

        Integer option = -1;
        boolean notRead = true;

        while (notRead) {

            try {
                System.out.print(TextColor.YELLOW_BOLD.getAnsi());
                option = scanner.nextInt();
                System.out.print(Console.ANSI_RESET);
                if (!validOptions.containsKey(option)) {
                    throw new Exception();
                }
                notRead = false;
            } catch (Exception ex) {
                Console.println(TextColor.RED, "(Wrong option)");
            }
        }

        return validOptions.get(option);
    }

}
