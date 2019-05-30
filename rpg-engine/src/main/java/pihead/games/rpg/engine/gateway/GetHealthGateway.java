package pihead.games.rpg.engine.gateway;

import pihead.games.rpg.engine.domain.entities.items.HealthItem;

import java.util.Optional;

public interface GetHealthGateway {

    Optional<HealthItem> getById(int id);
}
