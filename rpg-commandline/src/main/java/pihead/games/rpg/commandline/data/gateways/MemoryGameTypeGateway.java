package pihead.games.rpg.commandline.data.gateways;

import pihead.games.rpg.engine.domain.entities.GameType;
import pihead.games.rpg.engine.domain.entities.characters.PlayerType;
import pihead.games.rpg.engine.gateway.ListGameTypeGateway;
import pihead.games.rpg.engine.loader.GameLoader;
import pihead.games.rpg.residentevil.ResidentEvilGameLoader;

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

        GameLoader gameLoader = new ResidentEvilGameLoader();

        (new MemoryGameTypeGateway()).addAll(new ArrayList<GameType>() {{
            add(gameLoader.getGameType());
            add(GameType.builder().id(2).name("Silent Hill").addPlayerType(PlayerType.builder().id(3).build()).build());
        }});
    }
}
