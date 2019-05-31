package pihead.games.rpg.commandline.data.gateways;

import pihead.games.rpg.engine.domain.entities.GameType;
import pihead.games.rpg.engine.gateway.ListGameTypeGateway;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MemoryGameTypeGateway implements ListGameTypeGateway {

    private static Map<Integer, GameType> gameTypeMap = new HashMap<>();

    @Override
    public Collection<GameType> findAll() {
        return gameTypeMap.values();
    }

    public void addAll(Collection<GameType> gameTypes) {
        gameTypeMap.clear();
        for (GameType type : gameTypes) {
            gameTypeMap.put(type.getId(), type);
        }
    }

    static {
        (new MemoryGameTypeGateway()).addAll(new ArrayList<GameType>() {{
            add(GameType.builder().id(1).name("Resident Evil").build());
            add(GameType.builder().id(2).name("Silent Hill").build());
        }});
    }
}
