package domain;

import domain.entities.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class DefaultAttackEnemyTest {

    private AttackEnemy usecase;

    public DefaultAttackEnemyTest() {
        usecase = new DefaultAttackEnemy();
    }

    @Test
    public void playerStillAliveAndEnemyStillAlive() {

        // given
        final int initialPlayerHealth = 100;
        final int initialEnemyHealth = 100;
        final int initialEnemyDistance = 5;
        final int initialWeaponShots = 10;

        final Player player = Player.builder()
                .name("")
                .health(initialPlayerHealth)
                .maxHealth(initialPlayerHealth)
                .build();
        final WeaponType weaponType = new WeaponType("", 10, 10, 20);
        final Weapon weapon = Weapon.builder()
                .type(weaponType)
                .shots(initialWeaponShots)
                .build();
        final EnemyType zombie = EnemyType.builder()
                .name("Zombie")
                .velocity(1)
                .damage(5)
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
                .isEqualTo(initialPlayerHealth-zombie.getDamage());

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
                .name("")
                .health(1)
                .build();
        final WeaponType weaponType = new WeaponType("", 10, 10, 20);
        final Weapon weapon = Weapon.builder()
                .type(weaponType)
                .shots(10)
                .build();
        final EnemyType zombie = EnemyType.builder()
                .name("Zombie")
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
                .name("")
                .health(100)
                .build();
        final WeaponType weaponType = new WeaponType("", 10, 10, 20);
        final Weapon weapon = Weapon.builder()
                .type(weaponType)
                .shots(10)
                .build();
        final EnemyType zombie = EnemyType.builder()
                .name("Zombie")
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
