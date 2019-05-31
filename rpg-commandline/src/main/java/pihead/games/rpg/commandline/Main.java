package pihead.games.rpg.commandline;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.context.ApplicationContext;
import pihead.games.rpg.commandline.pages.InitialPage;

import java.util.Scanner;

// TODO: incomplete class
public class Main {

    private InitialPage initialPage;

    public Main(InitialPage initialPage) {
        this.initialPage = initialPage;
    }

    private void init() {
        initialPage.show();
    }

    public static void main(String[] args) {

        Console.initConsole();

        try {

            Main main = ApplicationContext.init(Main.class);
            main.init();

        } catch (Exception ex) {

            Console.println(TextColor.RED, ex.getClass().getName());
            for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
                Console.println(TextColor.RED, "\n" + stackTraceElement);
            }

        } finally {
            Console.initConsole();
        }

    }

}
