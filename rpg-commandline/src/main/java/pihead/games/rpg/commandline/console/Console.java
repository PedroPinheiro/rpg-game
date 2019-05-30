package pihead.games.rpg.commandline.console;

public class Console {

    private static String ANSI_RESET = "\u001B[0m";

    public final static void initConsole()
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

    public final static void printColor(TextColor textColor, String label) {
        System.out.print(textColor.getAnsi() + label + ANSI_RESET);
    }

    public final static void printLnColor(TextColor textColor, String label) {
        printColor(textColor, label + "\n");
    }
}
