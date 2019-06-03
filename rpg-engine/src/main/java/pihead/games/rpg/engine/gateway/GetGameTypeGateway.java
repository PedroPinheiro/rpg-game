package pihead.games.rpg.engine.gateway;

import pihead.games.rpg.engine.domain.entities.GameType;

import java.util.Optional;

public interface GetGameTypeGateway {
    Optional<GameType> findById(String gameTypeId);
}
