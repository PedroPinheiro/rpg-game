package pihead.games.rpg.engine.gateway;

import pihead.games.rpg.engine.domain.entities.items.Item;

import java.util.Optional;

public interface GetItemGateway {

    Optional<Item> getById(int id);
}
