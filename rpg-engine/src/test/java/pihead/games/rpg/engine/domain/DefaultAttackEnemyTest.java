package pihead.games.rpg.engine.domain;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pihead.games.rpg.engine.domain.entities.Game;
import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.items.Weapon;
import org.junit.Test;
import pihead.games.rpg.engine.domain.entities.items.WeaponType;
import pihead.games.rpg.engine.gateway.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;
import static pihead.games.rpg.engine.domain.entities.EntitiesTestHelper.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAttackEnemyTest {

    private AttackEnemy usecase;
    @Mock
    private GetGameGateway getGameGateway;
    @Mock
    private GetWeaponGateway getWeaponGateway;
    @Mock
    private GetEnemyGateway getEnemyGateway;
    @Mock
    private UpdateWeaponGateway updateWeaponGateway;
    @Mock
    private UpdateEnemyGateway updateEnemyGateway;

    @Before
    public void setUp() {
        usecase = new DefaultAttackEnemy(getGameGateway,
                getWeaponGateway,
                getEnemyGateway,
                updateWeaponGateway,
                updateEnemyGateway);
    }

    @Test
    public void enemyStillAlive() {

        // given
        final WeaponType weaponType = gimmeWeaponType().build();
        final Weapon weapon = gimmeWeapon().type(weaponType).build();
        final Enemy enemy = gimmeEnemy().build();

        final int initialEnemyHealth = enemy.getHealth();
        final int initialWeaponShots = weapon.getShots();

        when(getWeaponGateway.getById(weapon.getId())).thenReturn(Optional.of(weapon));
        when(getEnemyGateway.getById(enemy.getId())).thenReturn(Optional.of(enemy));

        // when
        usecase.attack(weapon.getId(), enemy.getId());

        // then
        assertThat(enemy.isAlive()).isTrue();
        assertThat(enemy.getHealth()).isEqualTo(initialEnemyHealth-weapon.getType().getDamage());
        assertThat(weapon.getShots()).isEqualTo(initialWeaponShots-1);

        verify(updateWeaponGateway, only()).updateWeapon(weapon);
        verify(updateEnemyGateway, only()).updateEnemy(enemy);
    }


    @Test
    public void enemyDead() {

        // given
        final int enemyHealth = 1;

        final WeaponType weaponType = gimmeWeaponType().damage(enemyHealth).build();
        final Weapon weapon = gimmeWeapon().type(weaponType).build();
        final Enemy enemy = gimmeEnemy().health(enemyHealth).build();

        final int initialWeaponShots = weapon.getShots();

        when(getWeaponGateway.getById(weapon.getId())).thenReturn(Optional.of(weapon));
        when(getEnemyGateway.getById(enemy.getId())).thenReturn(Optional.of(enemy));

        // when
        usecase.attack(weapon.getId(), enemy.getId());

        // then
        assertThat(enemy.isAlive()).isFalse();
        assertThat(enemy.getHealth()).isEqualTo(0);
        assertThat(weapon.getShots()).isEqualTo(initialWeaponShots-1);

        verify(updateWeaponGateway, only()).updateWeapon(weapon);
        verify(updateEnemyGateway, only()).updateEnemy(enemy);
    }
//
//    @Test
//    public void gameNotFound() {
//
//        // given
//        final int gameId = 1;
//        final WeaponType weaponType = gimmeWeaponType().build();
//        final Weapon weapon = gimmeWeapon().type(weaponType).build();
//        final Enemy enemy = gimmeEnemy().build();
//
//        when(getGameGateway.getById(gameId)).thenReturn(Optional.empty()); // return null game
//
//        // when
//        Throwable thrown = catchThrowable(() ->
//             usecase.attack(weapon.getId(), enemy.getId())
//        );
//
//        // then
//        assertThat(thrown)
//                .isInstanceOf(RuntimeException.class)
//                .hasNoCause()
//                .hasMessage("Game not found");
//    }

    @Test
    public void weaponNotFound() {

        // given
        final Game game = gimmeGame().build();
        final int weaponId = 1;
        final Enemy enemy = gimmeEnemy().build();

        when(getWeaponGateway.getById(weaponId)).thenReturn(Optional.empty());

        // when
        Throwable thrown = catchThrowable(() ->
                usecase.attack(weaponId, enemy.getId())
        );

        // then
        assertThat(thrown)
                .isInstanceOf(RuntimeException.class)
                .hasNoCause()
                .hasMessage("Weapon not found");
    }

    @Test
    public void enemyNotFound() {

        // given
        final Game game = gimmeGame().build();
        final WeaponType weaponType = gimmeWeaponType().build();
        final Weapon weapon = gimmeWeapon().type(weaponType).build();
        final int enemyId = 1;

        when(getWeaponGateway.getById(weapon.getId())).thenReturn(Optional.of(weapon));
        when(getEnemyGateway.getById(enemyId)).thenReturn(Optional.empty());  // return null enemy

        // when
        Throwable thrown = catchThrowable(() ->
                usecase.attack(weapon.getId(),enemyId)
        );

        // then
        assertThat(thrown)
                .isInstanceOf(RuntimeException.class)
                .hasNoCause()
                .hasMessage("Enemy not found");
    }


}
