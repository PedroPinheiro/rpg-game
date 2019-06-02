package pihead.games.rpg.residentevil;

import pihead.games.rpg.console.engine.RpgConsoleGame;
import pihead.games.rpg.engine.loader.GameLoader;

import java.io.InputStream;

public class ResidentEvilConsoleGame implements RpgConsoleGame {

    private static final String TITLE_PATH = "resident-evil.txt";

    @Override
    public InputStream getTitle() {
        return this.getClass().getClassLoader().getResourceAsStream(TITLE_PATH);
    }

    @Override
    public GameLoader getGameLoader() {
        return new ResidentEvilGameLoader();
    }
}
