package pihead.games.rpg.commandline.context;

import pihead.games.rpg.console.engine.RpgConsoleGame;
import pihead.games.rpg.engine.domain.entities.GameType;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ConsoleGameLoader {

    public List<GameType> getGameTypes() {

        return rpgConsoleGames()
                .map(g -> g.getGameLoader().getGameType())
                .collect(Collectors.toList());
    }

    public RpgConsoleGame getConsoleGame(String id) {
        return rpgConsoleGames()
                .filter(cg -> cg.getGameUUID().toString().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ConsoleGame not found"));
    }

    protected abstract Stream<RpgConsoleGame> rpgConsoleGames();

}
