package pihead.games.rpg.residentevil;

import pihead.games.rpg.console.engine.RpgConsoleGame;
import pihead.games.rpg.engine.loader.GameLoader;

import java.io.InputStream;
import java.util.UUID;

public class ResidentEvilConsoleGame implements RpgConsoleGame {

    private static final String TITLE_PATH = "resident-evil.txt";

    @Override
    public UUID getGameUUID() {
        return Config.RESIDENT_EVIL_UUID;
    }

    @Override
    public InputStream getTitle() {
        return this.getClass().getClassLoader().getResourceAsStream(TITLE_PATH);
    }

    @Override
    public GameLoader getGameLoader() {
        return new ResidentEvilGameLoader();
    }
}
