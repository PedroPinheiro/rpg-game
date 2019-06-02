package pihead.games.rpg.console.engine;

import pihead.games.rpg.engine.loader.GameLoader;

import java.io.InputStream;

public interface RpgConsoleGame {

    InputStream getTitle();

    GameLoader getGameLoader();
}
