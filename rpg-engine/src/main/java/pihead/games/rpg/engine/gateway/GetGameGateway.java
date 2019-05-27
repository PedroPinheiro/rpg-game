package pihead.games.rpg.engine.gateway;

import pihead.games.rpg.engine.domain.entities.Game;

import java.util.Optional;

public interface GetGameGateway {

    Optional<Game> getById(int gameId);
}
