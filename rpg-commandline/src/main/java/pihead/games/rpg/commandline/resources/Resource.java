package pihead.games.rpg.commandline.resources;

public enum Resource {
    PIHEAD_GAMES("pihead-games.txt"),
    RESIDENT_EVIL("resident-evil.txt");

    String path;

    Resource(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
