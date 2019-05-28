package pihead.games.rpg.engine.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pihead.games.rpg.engine.domain.entities.Room;
import pihead.games.rpg.engine.gateway.GetRoomGateway;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;
import static pihead.games.rpg.engine.domain.TestHelper.nextPositiveInt;
import static pihead.games.rpg.engine.domain.entities.EntitiesTestHelper.gimmeRoom;

@RunWith(MockitoJUnitRunner.class)
public class DefaultEnterRoomTest {

    private EnterRoom usecase;

    @Mock
    private GetRoomGateway getRoomGateway;

    @Before
    public void setup() {
        usecase = new DefaultEnterRoom(getRoomGateway);
    }

    @Test
    public void success() {

        // given
        Room room = gimmeRoom().build();

        when(getRoomGateway.getById(room.getId())).thenReturn(Optional.of(room));

        // when
        EnterRoom.ResponseModel responseModel = usecase.enter(room.getId());

        // then
        int i = 1;

    }


    @Test
    public void roomNotFound() {

        // given
        int roomId = nextPositiveInt();

        when(getRoomGateway.getById(roomId)).thenReturn(Optional.empty());

        // when
        Throwable thrown = catchThrowable(() ->
                usecase.enter(roomId)
        );

        // then
        assertThat(thrown).isExactlyInstanceOf(RuntimeException.class)
                .hasMessage("Room not found");

    }

}
