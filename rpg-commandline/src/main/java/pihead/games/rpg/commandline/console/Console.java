package pihead.games.rpg.commandline.console;

import pihead.games.rpg.commandline.resources.Resource;

import java.io.*;

import static pihead.games.rpg.commandline.resources.ResourceReader.getResource;

public class Console {

    public static String ANSI_RESET = "\u001B[0m";

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

    public final static void printResource(Resource resource) {

        printResource(null, resource);
    }

    public final static void printResource(TextColor textColor, Resource resource) {

        InputStream in = getResource(resource);

        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(in, "UTF8"));

            String str;
            if (textColor != null) {
                System.out.print(textColor.getAnsi());
            }
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }
            if (textColor != null) {
                System.out.print(Console.ANSI_RESET);
            }

            reader.close();
            in.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println();
    }
}
