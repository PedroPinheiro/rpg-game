package pihead.games.rpg.engine.gateway;

import pihead.games.rpg.engine.loader.GameLoader;

import java.util.Optional;

public interface GetGameLoaderGateway {
    Optional<GameLoader> getByGameTypeId(String gameTypeId);
}
