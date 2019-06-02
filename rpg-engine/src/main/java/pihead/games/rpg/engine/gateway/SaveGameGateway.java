package pihead.games.rpg.engine.gateway;

import pihead.games.rpg.engine.domain.entities.Game;

public interface SaveGameGateway {
    Game save(Game game);
}
