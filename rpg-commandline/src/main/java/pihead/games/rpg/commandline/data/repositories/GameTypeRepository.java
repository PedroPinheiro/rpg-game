package pihead.games.rpg.commandline.data.repositories;

import pihead.games.rpg.engine.domain.entities.GameType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GameTypeRepository {

    private static Map<Integer, GameType> gameType = new HashMap<>();

    public Collection<GameType> listAll() {
        return gameType.values();
    }
}
