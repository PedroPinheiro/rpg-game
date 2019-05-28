package pihead.games.rpg.engine.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pihead.games.rpg.engine.domain.entities.Game;
import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.domain.entities.items.Weapon;
import pihead.games.rpg.engine.domain.exceptions.PlayerIsNotCarryingWeapon;
import pihead.games.rpg.engine.gateway.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;
import static pihead.games.rpg.engine.domain.TestHelper.nextPositiveInt;
import static pihead.games.rpg.engine.domain.entities.EntitiesTestHelper.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAttackOnRoomTest {

    private AttackOnRoom usecase;
    @Mock
    private GetPlayerGateway getPlayerGateway;
    @Mock
    private CalculateEnemiesDistances calculateEnemiesDistances;
    @Mock
    private AttackEnemy attackEnemy;
    @Mock
    private PlayerReceiveDamage playerReceiveDamage;

    @Before
    public void setUp() {
        usecase = new DefaultAttackOnRoom(getPlayerGateway,
                calculateEnemiesDistances,
                attackEnemy,
                playerReceiveDamage);
    }

    @Test
    public void attackDone() {

        // given
        final int gameId = nextPositiveInt();
        final int roomId = nextPositiveInt();
        final int chosenEnemyId = nextPositiveInt();
        final Player player = gimmePlayer().build();
        final Weapon weapon = gimmeWeapon().build();
        player.chooseWeapon(weapon);

        when(getPlayerGateway.getById(player.getId())).thenReturn(Optional.of(player));

        // when
        Throwable thrown = catchThrowable(() -> {
            usecase.attack(gameId, roomId, player.getId(), chosenEnemyId);
        });

        // then
        assertThat(thrown).isNull();

        verify(calculateEnemiesDistances, only()).calculate(roomId);
        verify(attackEnemy, only()).attack(weapon.getId(), chosenEnemyId);
        verify(playerReceiveDamage, only()).receiveDamage(player.getId(), roomId);

    }

    @Test
    public void playerIsNotCarryingWeapon() {

        // given
        final Player player = gimmePlayer().build();
        final Game game = gimmeGame().player(player).build();
        int roomId = nextPositiveInt();
        int chosenEnemyId = nextPositiveInt();

        when(getPlayerGateway.getById(player.getId())).thenReturn(Optional.of(player));

        // when
        Throwable thrown = catchThrowable(() -> {
            usecase.attack(game.getId(), roomId, player.getId(), chosenEnemyId);
        });

        // then
        assertThat(thrown).isInstanceOf(PlayerIsNotCarryingWeapon.class);

    }

    @Test
    public void playerNotFound() {

        // given
        final int playerId = nextPositiveInt();
        final int gameId = nextPositiveInt();
        int roomId = nextPositiveInt();
        int chosenEnemyId = nextPositiveInt();

        when(getPlayerGateway.getById(playerId)).thenReturn(Optional.empty());

        // when
        Throwable thrown = catchThrowable(() -> {
            usecase.attack(gameId, roomId, playerId, chosenEnemyId);
        });

        // then
        assertThat(thrown).isExactlyInstanceOf(RuntimeException.class)
            .hasMessage("Player not found");

    }

}
