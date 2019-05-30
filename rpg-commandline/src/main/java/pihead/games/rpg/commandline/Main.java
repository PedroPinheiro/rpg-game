package pihead.games.rpg.commandline;

import pihead.games.rpg.commandline.pages.InitialPage;

import java.io.IOException;

// TODO: incomplete class
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        clearConsole();

        try {
            InitialPage initialPage = new InitialPage();

            initialPage.show();

        } finally {
            clearConsole();
        }
    }

    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }
}
