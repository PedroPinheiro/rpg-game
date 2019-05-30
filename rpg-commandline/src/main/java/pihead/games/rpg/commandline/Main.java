package pihead.games.rpg.commandline;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.pages.InitialPage;

import java.io.IOException;

// TODO: incomplete class
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Console.initConsole();

        try {

            InitialPage initialPage = new InitialPage();

            initialPage.show();

        } finally {
            Console.initConsole();
        }
    }

}
