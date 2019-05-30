package pihead.games.rpg.commandline.utils;

import pihead.games.rpg.commandline.Main;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// TODO: incomplete class
public class ResourceReader {

    public enum Resource {
        PIHEAD_GAMES,
        RESIDENT_EVIL
    }

    private static Map<Resource, String> resources = new HashMap<>();


    private static ClassLoader classLoader = (new Main()).getClass().getClassLoader();

    static {
        resources.put(Resource.PIHEAD_GAMES, "pihead-games.txt");
        resources.put(Resource.RESIDENT_EVIL, "resident-evil.txt");
    }

    public static String getResourceAsString(Resource resource) {

        String resourceValue = resources.get(resource);

        try (InputStream inputStream = classLoader.getResourceAsStream(resourceValue)) {

            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
