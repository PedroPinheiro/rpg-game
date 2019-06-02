package pihead.games.rpg.commandline.context;

import pihead.games.rpg.console.engine.RpgConsoleGame;
import pihead.games.rpg.engine.domain.entities.GameType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ConsoleGameLoader {

    private static Map<String, List<RpgConsoleGame>> rpgGames = new HashMap<>();

    public List<GameType> getGameTypes() {

        return rpgConsoleGames()
                .map(g -> g.getGameLoader().getGameType())
                .collect(Collectors.toList());
    }

    protected abstract Stream<RpgConsoleGame> rpgConsoleGames();

}
