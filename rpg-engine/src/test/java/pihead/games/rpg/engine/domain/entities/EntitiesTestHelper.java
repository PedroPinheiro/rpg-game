package pihead.games.rpg.engine.domain.entities;

import pihead.games.rpg.engine.domain.TestHelper;
import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.domain.entities.items.Weapon;
import pihead.games.rpg.engine.domain.entities.items.WeaponType;

public final class EntitiesTestHelper {

    public static Game.Builder gimmeGame() {
        return Game.builder()
                .id(TestHelper.nextPositiveInt());
    }

    public static Weapon.Builder gimmeWeapon() {
        return Weapon.builder()
                .id(TestHelper.nextPositiveInt())
                .type(gimmeWeaponType().build());
    }

    public static WeaponType.Builder gimmeWeaponType() {
        final int defaultShots = 10;
        final int defaultDamage = 1;
        return WeaponType.builder()
                .shots(defaultShots)
                .damage(defaultDamage);
    }

    public static Enemy.Builder gimmeEnemy() {
        int defaultHealth = 100;
        return Enemy.builder()
                .id(TestHelper.nextPositiveInt())
                .health(defaultHealth);
    }

    public static Player.Builder gimmePlayer() {
        int defaultHealth = 100;
        return Player.builder()
                .id(TestHelper.nextPositiveInt())
                .health(defaultHealth);
    }
}
