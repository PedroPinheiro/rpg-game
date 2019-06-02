package pihead.games.rpg.commandline.context;

import pihead.games.rpg.commandline.config.ConfigProperties;
import pihead.games.rpg.console.engine.RpgConsoleGame;

import java.util.stream.Stream;

public class PropertiesConsoleGameLoader extends ConsoleGameLoader {

    private static final String GAMES_KEY = "rpg.games";

    protected Stream<RpgConsoleGame> rpgConsoleGames() {

        String[] strGames = ConfigProperties.get(GAMES_KEY).split(",");

        return Stream.of(strGames).map(s -> {
            try {
                Class<RpgConsoleGame> theClass = (Class<RpgConsoleGame>) Class.forName(s);
                return theClass.getDeclaredConstructor().newInstance();
            } catch (Exception  e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
