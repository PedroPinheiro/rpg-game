package pihead.games.rpg.commandline.data.repositories;

import pihead.games.rpg.engine.domain.entities.GameType;
import pihead.games.rpg.engine.domain.entities.characters.PlayerType;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerTypeRepository {

    private static Map<Integer, PlayerType> playerTypeIdIndex = new HashMap<>();
    private static Map<String, List<PlayerType>> gameTypeIdIndex = new HashMap<>();

    public PlayerType findById(int playerId) {
        return playerTypeIdIndex.get(playerId);
    }

    public Collection<PlayerType> findByGameTypeId(String gameTypeId) {
        return gameTypeIdIndex.get(gameTypeId);
    }

    void addPlayerTypesFromGameType(GameType gameType) {

        gameTypeIdIndex.put(gameType.getId(), gameType.getPlayerTypes());

        for (PlayerType playerType : gameType.getPlayerTypes()) {
            playerTypeIdIndex.put(playerType.getId(), playerType);
        }
    }
}
