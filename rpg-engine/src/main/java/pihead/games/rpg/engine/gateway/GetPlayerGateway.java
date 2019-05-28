package pihead.games.rpg.engine.gateway;

import pihead.games.rpg.engine.domain.entities.characters.Player;

import java.util.Optional;

public interface GetPlayerGateway {

    Optional<Player> getById(int playerId);
}
