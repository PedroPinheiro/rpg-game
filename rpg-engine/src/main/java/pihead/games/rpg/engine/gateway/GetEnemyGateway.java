package pihead.games.rpg.engine.gateway;

import pihead.games.rpg.engine.domain.entities.characters.Enemy;

import java.util.Optional;

public interface GetEnemyGateway {

    Optional<Enemy> getById(int weaponId);
}
