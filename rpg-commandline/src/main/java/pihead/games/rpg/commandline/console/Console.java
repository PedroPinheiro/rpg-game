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

    public final static void print(TextColor textColor, String label) {
        System.out.print(textColor.getAnsi() + label + ANSI_RESET);
    }

    public final static void println(TextColor textColor, String label) {
        print(textColor, label + "\n");
    }

    public final static void print(String label) {
        System.out.print(label + ANSI_RESET);
    }

    public final static void println(String label) {
        print(label + "\n");
    }
}
