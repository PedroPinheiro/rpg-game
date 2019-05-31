package pihead.games.rpg.engine.gateway;

import pihead.games.rpg.engine.domain.entities.GameType;

import java.util.List;

public interface GetGameTypeGateway {

    List<GameType> findAll();
}
