package pihead.games.rpg.engine.gateway;

import pihead.games.rpg.engine.domain.entities.GameType;

import java.util.Collection;

public interface ListGameTypeGateway {

    Collection<GameType> findAll();
}
