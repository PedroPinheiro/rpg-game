package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.characters.EnemyType;
import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.domain.entities.characters.PlayerType;
import pihead.games.rpg.engine.domain.entities.items.Weapon;
import pihead.games.rpg.engine.domain.entities.items.WeaponType;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class DefaultAttackEnemyTest {

    private AttackEnemy usecase;

    public DefaultAttackEnemyTest() {
        usecase = new DefaultAttackEnemy();
    }

    @Test
    public void playerStillAliveAndEnemyStillAliveWithoutDamageOnPlayer() {

        // given
        final int initialPlayerHealth = 100;
        final int initialEnemyHealth = 100;
        final int initialEnemyDistance = 5;
        final int minEnemyDistanceToAttack = 0;
        final int initialWeaponShots = 10;

        final PlayerType playerType = PlayerType.builder()
                .maxHealth(initialPlayerHealth)
                .build();
        final Player player = Player.builder()
                .health(initialPlayerHealth)
                .type(playerType)
                .build();
        final WeaponType weaponType = WeaponType.builder()
                .range(1)
                .shots(initialWeaponShots)
                .damage(10)
                .range(10)
                .build();
        final Weapon weapon = Weapon.builder()
                .type(weaponType)
                .build();
        final EnemyType zombie = EnemyType.builder()
                .velocity(1)
                .damage(5)
                .minAttackDistance(minEnemyDistanceToAttack)
                .build();
        final Enemy enemy = Enemy.builder()
                .type(zombie)
                .health(initialEnemyHealth)
                .distance(initialEnemyDistance)
                .build();

        // when
        final AttackEnemy.ResponseModel response = usecase.attack(player, weapon, enemy);

        // then
        assertThat(response.getPlayer().isAlive()).isTrue();
        assertThat(response.getPlayer().getHealth())
                .isEqualTo(initialPlayerHealth);

        assertThat(response.getEnemy().isAlive()).isTrue();
        assertThat(response.getEnemy().getHealth())
                .isEqualTo(initialEnemyHealth- weapon.getType().getDamage());
        assertThat(response.getEnemy().getDistance())
                .isEqualTo(initialEnemyDistance);
        assertThat(response.getWeapon().getShots())
                .isEqualTo(initialWeaponShots-1);
    }

    @Test
    public void playerStillAliveAndEnemyStillAliveWithDamageOnPlayer() {

        // given
        final int initialPlayerHealth = 100;
        final int initialEnemyHealth = 100;
        final int initialEnemyDistance = 3;
        final int minEnemyDistanceToAttack = 5;
        final int initialWeaponShots = 10;

        final PlayerType playerType = PlayerType.builder()
                .maxHealth(initialPlayerHealth)
                .build();
        final Player player = Player.builder()
                .health(initialPlayerHealth)
                .type(playerType)
                .build();
        final WeaponType weaponType = WeaponType.builder()
                .range(1)
                .shots(10)
                .damage(10)
                .range(10)
                .build();
        final Weapon weapon = Weapon.builder()
                .type(weaponType)
                .build();
        final EnemyType zombie = EnemyType.builder()
                .velocity(1)
                .damage(5)
                .minAttackDistance(minEnemyDistanceToAttack)
                .build();
        final Enemy enemy = Enemy.builder()
                .type(zombie)
                .health(initialEnemyHealth)
                .distance(initialEnemyDistance)
                .build();

        // when
        final AttackEnemy.ResponseModel response = usecase.attack(player, weapon, enemy);

        // then
        assertThat(response.getPlayer().isAlive()).isTrue();
        assertThat(response.getPlayer().getHealth())
                .isEqualTo(initialPlayerHealth-response.getEnemy().getType().getDamage());

        assertThat(response.getEnemy().isAlive()).isTrue();
        assertThat(response.getEnemy().getHealth())
                .isEqualTo(initialEnemyHealth- weapon.getType().getDamage());
        assertThat(response.getEnemy().getDistance())
                .isEqualTo(initialEnemyDistance);
        assertThat(response.getWeapon().getShots())
                .isEqualTo(initialWeaponShots-1);
    }

    @Test
    public void playerIsDeadAndEnemyStillAlive() {

        // given
        final Player player = Player.builder()
                .health(1)
                .build();
        final WeaponType weaponType = WeaponType.builder()
                .range(1)
                .shots(10)
                .damage(10)
                .range(10)
                .build();
        final Weapon weapon = Weapon.builder()
                .type(weaponType)
                .build();
        final EnemyType zombie = EnemyType.builder()
                .velocity(1)
                .damage(5)
                .build();
        final Enemy enemy = Enemy.builder()
                .type(zombie)
                .health(2)
                .build();

        // when
        final AttackEnemy.ResponseModel response = usecase.attack(player, weapon, enemy);

        // then
        assertThat(response.getPlayer().isAlive()).isFalse();
    }

    @Test
    public void playerStillAliveAndEnemyIsDead() {

        // given
        final Player player = Player.builder()
                .health(100)
                .build();
        final WeaponType weaponType = WeaponType.builder()
                .range(1)
                .shots(10)
                .damage(10)
                .range(10)
                .build();
        final Weapon weapon = Weapon.builder()
                .type(weaponType)
                .build();
        final EnemyType zombie = EnemyType.builder()
                .velocity(1)
                .damage(5)
                .build();
        final Enemy enemy = Enemy.builder()
                .type(zombie)
                .health(2)
                .build();

        // when
        final AttackEnemy.ResponseModel response = usecase.attack(player, weapon, enemy);

        // then
        assertThat(response.getEnemy().isAlive()).isFalse();
    }
}
