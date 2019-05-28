package pihead.games.rpg.engine.domain.entities;

import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.characters.EnemyType;
import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.domain.entities.characters.PlayerType;
import pihead.games.rpg.engine.domain.entities.items.Weapon;
import pihead.games.rpg.engine.domain.entities.items.WeaponType;

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
        return EnemyType.builder()
                .name(nextString())
                .maxHealth(defaultMaxHealth);
    }

    public static Player.Builder gimmePlayer() {
        return Player.builder()
                .id(nextPositiveInt())
                .type(gimmePlayerType().build());
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
}
