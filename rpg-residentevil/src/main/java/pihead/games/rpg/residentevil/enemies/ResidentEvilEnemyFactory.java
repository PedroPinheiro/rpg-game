package pihead.games.rpg.residentevil.enemies;

import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.characters.EnemyType;

import java.util.HashMap;
import java.util.Map;

import static pihead.games.rpg.residentevil.enemies.ResidentEvilEnemy.*;

public class ResidentEvilEnemyFactory {

    private static Map<ResidentEvilEnemy, EnemyType> enemyTypes = new HashMap<>();

    public static Enemy buildZombieLevel1(int distance) {
        return Enemy.builder()
                .type(enemyTypes.get(ZOMBIE_LEVEL_1))
                .distance(distance)
                .build();
    }

    public static Enemy buildZombieLevel2(int distance) {
        return Enemy.builder()
                .type(enemyTypes.get(ZOMBIE_LEVEL_2))
                .distance(distance)
                .build();
    }

    public static Enemy buildZombieLevel3(int distance) {
        return Enemy.builder()
                .type(enemyTypes.get(ZOMBIE_LEVEL_3))
                .distance(distance)
                .build();
    }

    public static Enemy buildNemesis(int distance) {
        return Enemy.builder()
                .type(enemyTypes.get(NEMESIS))
                .distance(distance)
                .build();
    }

    static {

        EnemyType zombieLevel1 = EnemyType.builder()
                .damage(1)
                .maxHealth(5)
                .minAttackDistance(0)
                .velocity(1)
                .name("Zombie Level 1")
                .build();
        enemyTypes.put(ZOMBIE_LEVEL_1, zombieLevel1);

        EnemyType zombieLevel2 = EnemyType.builder()
                .damage(3)
                .maxHealth(10)
                .minAttackDistance(0)
                .velocity(1)
                .name("Zombie Level 2")
                .build();
        enemyTypes.put(ZOMBIE_LEVEL_2, zombieLevel2);

        EnemyType zombieLevel3 = EnemyType.builder()
                .damage(7)
                .maxHealth(20)
                .minAttackDistance(0)
                .velocity(2)
                .name("Zombie Level 3")
                .build();
        enemyTypes.put(ZOMBIE_LEVEL_3, zombieLevel3);

        EnemyType nemesis = EnemyType.builder()
                .damage(7)
                .maxHealth(20)
                .minAttackDistance(4)
                .velocity(2)
                .name("Nemesis")
                .build();
        enemyTypes.put(NEMESIS, nemesis);

    }

}
