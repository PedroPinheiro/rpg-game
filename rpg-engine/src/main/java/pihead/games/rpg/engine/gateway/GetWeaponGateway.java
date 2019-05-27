package pihead.games.rpg.engine.gateway;

import pihead.games.rpg.engine.domain.entities.items.Weapon;

import java.util.Optional;

public interface GetWeaponGateway {

    Optional<Weapon> getById(int weaponId);
}
