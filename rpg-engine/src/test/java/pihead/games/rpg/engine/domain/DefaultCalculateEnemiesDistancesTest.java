package pihead.games.rpg.engine.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pihead.games.rpg.engine.domain.entities.Room;
import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.gateway.GetRoomGateway;
import pihead.games.rpg.engine.gateway.UpdateEnemyGateway;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;
import static pihead.games.rpg.engine.domain.TestHelper.*;
import static pihead.games.rpg.engine.domain.entities.EntitiesTestHelper.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCalculateEnemiesDistancesTest {

    private CalculateEnemiesDistances usecase;

    @Mock
    private GetRoomGateway getRoomGateway;
    @Mock
    private UpdateEnemyGateway updateEnemyGateway;

    @Before
    public void setup() {
        usecase = new DefaultCalculateEnemiesDistances(getRoomGateway, updateEnemyGateway);
    }

    @Test
    public void success() {

        // given
        Room.Builder roomBuilder = gimmeRoom();
        int numberOfEnemies = 3;
        for (int i=0; i<numberOfEnemies; i++) {
            Enemy enemy = gimmeEnemy().build();
            roomBuilder.addEnemy(enemy);
        }
        Room room = roomBuilder.build();

        when(getRoomGateway.getById(room.getId())).thenReturn(Optional.of(room));

        // when
        usecase.calculate(room.getId());

        // then
        room.getEnemies().forEach(enemy -> {
            verify(updateEnemyGateway).updateEnemy(enemy);
        });
        verify(updateEnemyGateway, times(numberOfEnemies)).updateEnemy(any(Enemy.class));
    }

    @Test
    public void roomNotFound() {

        // given
        int roomId = nextPositiveInt();

        when(getRoomGateway.getById(roomId)).thenReturn(Optional.empty());

        // when
        Throwable thrown = catchThrowable(() ->
                usecase.calculate(roomId)
        );

        // then
        assertThat(thrown).isExactlyInstanceOf(RuntimeException.class)
                .hasMessage("Room not found");

    }
}
