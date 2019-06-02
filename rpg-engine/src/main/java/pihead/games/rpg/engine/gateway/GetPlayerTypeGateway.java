package pihead.games.rpg.engine.gateway;

import pihead.games.rpg.engine.domain.entities.characters.PlayerType;

import java.util.Optional;

public interface GetPlayerTypeGateway {
    Optional<PlayerType> getById(int playerTypeId);
}
