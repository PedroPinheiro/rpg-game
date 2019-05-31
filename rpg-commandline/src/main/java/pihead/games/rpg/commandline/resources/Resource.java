package pihead.games.rpg.commandline.resources;

import java.io.InputStream;

public enum Resource {

    PIHEAD_GAMES("pihead-games.txt"),
    RESIDENT_EVIL("resident-evil.txt"),
    SILENT_HILL("silent-hill.txt");

    private String path;

    private ClassLoader classLoader;

    Resource(String path) {
        this.path = path;
        classLoader = this.getClass().getClassLoader();
    }

    public String getPath() {
        return path;
    }

    public InputStream asStream() {
        return classLoader.getResourceAsStream(this.getPath());
    }
}
