package pihead.games.rpg.engine.domain.entities;

import pihead.games.rpg.engine.domain.entities.backpack.Backpack;
import pihead.games.rpg.engine.domain.entities.characters.*;
import pihead.games.rpg.engine.domain.entities.items.*;

import static pihead.games.rpg.engine.domain.TestHelper.*;

public final class EntitiesTestHelper {

    public static Game.Builder gimmeGame() {
        return Game.builder()
                .id(nextPositiveInt())
                .type(gimmeGameType().build());
    }
    public static GameType.Builder gimmeGameType() {
        return GameType.builder()
                .id(nextPositiveInt())
                .name(nextString())
                .description(nextString());
    }

    public static Weapon.Builder gimmeWeapon() {
        return Weapon.builder()
                .id(nextPositiveInt())
                .type(gimmeWeaponType().build());
    }

    public static WeaponType.Builder gimmeWeaponType() {
        final int defaultShots = 10;
        final int defaultDamage = 1;
        return WeaponType.builder()
                .name(nextString())
                .shots(defaultShots)
                .damage(defaultDamage);
    }

    public static Enemy.Builder gimmeEnemy() {
        return Enemy.builder()
                .id(nextPositiveInt())
                .type(gimmeEnemyType().build());
    }

    public static EnemyType.Builder gimmeEnemyType() {
        int defaultMaxHealth = 100;
        int defaultDamage = 10;
        return EnemyType.builder()
                .name(nextString())
                .maxHealth(defaultMaxHealth)
                .damage(defaultDamage);
    }

    public static Player.Builder gimmePlayer() {
        int defaultHealth = 100;
        return Player.builder()
                .id(nextPositiveInt())
                .health(defaultHealth)
                .type(gimmePlayerType().build())
                .backpack(gimmeBackpack().build());
    }

    public static Backpack.Builder gimmeBackpack() {
        int defaultMaxSlots = 10;
        return Backpack.builder()
                .id(nextPositiveInt())
                .maxSlots(defaultMaxSlots);
    }

    public static PlayerType.Builder gimmePlayerType() {
        int defaultHealth = 100;
        return PlayerType.builder()
                .id(nextPositiveInt())
                .maxHealth(defaultHealth)
                .name(nextString());
    }

    public static Room.Builder gimmeRoom() {
        return Room.builder()
                .id(nextPositiveInt())
                .name(nextString())
                .description(nextString());
    }

    public static Room.Builder gimmeEmptyRoom() {
        return Room.builder()
                .id(nextPositiveInt())
                .name(nextString())
                .description(nextString());
    }

    public static HealthItem.Builder gimmeHealthItem() {
        return HealthItem.builder()
                .id(nextPositiveInt())
                .type(gimmeHealthItemType().build());
    }

    public static HealthItemType.Builder gimmeHealthItemType() {
        int defaultHealthPower = 10;
        return HealthItemType.builder()
                .name(nextString())
                .healthPower(defaultHealthPower);
    }
}
