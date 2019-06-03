package pihead.games.rpg.commandline.data.repositories;

import pihead.games.rpg.engine.domain.entities.GameType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GameTypeRepository {

    private PlayerTypeRepository playerTypeRepository;

    private static Map<String, GameType> gameTypeMap = new HashMap<>();

    public GameTypeRepository(PlayerTypeRepository playerTypeRepository) {
        this.playerTypeRepository = playerTypeRepository;
    }

    public Collection<GameType> findAll() {
        return gameTypeMap.values();
    }

    public GameType findById(String id) {
        return gameTypeMap.get(id);
    }

    public void addAll(Collection<GameType> gameTypes) {
        gameTypeMap.clear();
        for (GameType type : gameTypes) {
            gameTypeMap.put(type.getId(), type);
            playerTypeRepository.addPlayerTypesFromGameType(type);
        }
    }
}
