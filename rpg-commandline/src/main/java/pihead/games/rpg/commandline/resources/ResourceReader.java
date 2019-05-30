package pihead.games.rpg.commandline.resources;

import pihead.games.rpg.commandline.Main;

import java.io.InputStream;

// TODO: incomplete class
public class ResourceReader {

    private static ClassLoader classLoader = (new Main()).getClass().getClassLoader();

    public static InputStream getResource(Resource resource) {

        String resourcePath = resource.getPath();

        InputStream in = null;

        try {
            in = classLoader.getResourceAsStream(resourcePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return in;
    }


}
