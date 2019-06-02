package pihead.games.rpg.console.engine;

import pihead.games.rpg.engine.loader.GameLoader;

import java.io.InputStream;
import java.util.UUID;

public interface RpgConsoleGame {

    UUID getGameUUID();

    InputStream getTitle();

    GameLoader getGameLoader();
}
