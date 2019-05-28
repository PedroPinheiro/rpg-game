package pihead.games.rpg.engine.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pihead.games.rpg.engine.domain.entities.Room;
import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.gateway.GetPlayerGateway;
import pihead.games.rpg.engine.gateway.GetRoomGateway;
import pihead.games.rpg.engine.gateway.UpdatePlayerGateway;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;
import static pihead.games.rpg.engine.domain.TestHelper.nextPositiveInt;
import static pihead.games.rpg.engine.domain.entities.EntitiesTestHelper.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultPlayerReceiveDamageTest {

    private PlayerReceiveDamage usecase;
    @Mock
    private GetRoomGateway getRoomGateway;
    @Mock
    private GetPlayerGateway getPlayerGateway;
    @Mock
    private UpdatePlayerGateway updatePlayerGateway;

    @Before
    public void setup() {
        usecase = new DefaultPlayerReceiveDamage(getRoomGateway, getPlayerGateway, updatePlayerGateway);
    }

    @Test
    public void success() {

        // Given
        Room.Builder roomBuilder = gimmeRoom();
        int numberOfEnemies = 3;
        for (int i=0; i<numberOfEnemies; i++) {
            Enemy enemy = gimmeEnemy().build();
            roomBuilder.addEnemy(enemy);
        }
        Room room = roomBuilder.build();

        Player player = gimmePlayer().build();
        int initialPlayerHealth = player.getHealth();

        when(getRoomGateway.getById(room.getId())).thenReturn(Optional.of(room));
        when(getPlayerGateway.getById(player.getId())).thenReturn(Optional.of(player));

        // When
        usecase.receiveDamage(player.getId(), room.getId());

        // Then
        int totalDamage = room.getEnemies().stream()
                .map(e -> e.getType().getDamage())
                .mapToInt(Integer::intValue)
                .sum();

        assertThat(player.getHealth()).isEqualTo(initialPlayerHealth-totalDamage);

        verify(updatePlayerGateway, only()).updatePlayer(player);


    }

    @Test
    public void roomNotFound() {

        // Given
        int roomId = nextPositiveInt();
        int playerId = nextPositiveInt();

        when(getRoomGateway.getById(roomId)).thenReturn(Optional.empty());

        // When
        Throwable thrown = catchThrowable(() ->
                usecase.receiveDamage(playerId, roomId)
        );

        // Then
        assertThat(thrown).isExactlyInstanceOf(RuntimeException.class)
                .hasMessage("Room not found");

    }

    @Test
    public void playerNotFound() {

        // Given
        Room room = gimmeRoom().build();
        int playerId = nextPositiveInt();

        when(getRoomGateway.getById(room.getId())).thenReturn(Optional.of(room));
        when(getPlayerGateway.getById(playerId)).thenReturn(Optional.empty());


        // When
        Throwable thrown = catchThrowable(() ->
                usecase.receiveDamage(playerId, room.getId())
        );

        // Then
        assertThat(thrown).isExactlyInstanceOf(RuntimeException.class)
                .hasMessage("Player not found");

    }
}
