package pihead.games.rpg.engine.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pihead.games.rpg.engine.domain.entities.Room;
import pihead.games.rpg.engine.domain.entities.RoomSide;
import pihead.games.rpg.engine.gateway.GetRoomGateway;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;
import static pihead.games.rpg.engine.domain.TestHelper.*;
import static pihead.games.rpg.engine.domain.assertions.EnterRoomResponseAssertion.assertThatEnterRoomResponse;
import static pihead.games.rpg.engine.domain.entities.EntitiesTestHelper.*;

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
    public void emptyRoom() {

        // given
        Room room = gimmeRoom().build();

        when(getRoomGateway.getById(room.getId())).thenReturn(Optional.of(room));

        // when
        EnterRoom.ResponseModel responseModel = usecase.enter(room.getId());

        // then
        assertThat(responseModel.getRoomId()).isEqualTo(room.getId());
        assertThat(responseModel.getRoomName()).isEqualTo(room.getName());

        assertThatEnterRoomResponse(responseModel).hasEnemiesOf(room);
        assertThatEnterRoomResponse(responseModel).hasItemsOf(room);
        assertThatEnterRoomResponse(responseModel).hasNextRoomsOf(room);

    }
    @Test
    public void fullRoom() {

        // given
        Room room = gimmeRoom()
                .addEnemy(gimmeEnemy().build())
                .addEnemy(gimmeEnemy().build())
                .addEnemy(gimmeEnemy().build())
                .addItem(RoomSide.Direction.LEFT, gimmeHealthItem().build())
                .addItem(RoomSide.Direction.LEFT, gimmeHealthItem().build())
                .addItem(RoomSide.Direction.LEFT, gimmeHealthItem().build())
                .addItem(RoomSide.Direction.RIGHT, gimmeWeapon().build())
                .addItem(RoomSide.Direction.RIGHT, gimmeWeapon().build())
                .addItem(RoomSide.Direction.RIGHT, gimmeWeapon().build())
                .addItem(RoomSide.Direction.FRONT, gimmeWeapon().build())
                .addItem(RoomSide.Direction.FRONT, gimmeWeapon().build())
                .addItem(RoomSide.Direction.FRONT, gimmeWeapon().build())
                .addNextRoom(RoomSide.Direction.LEFT, gimmeRoom().build())
                .addNextRoom(RoomSide.Direction.RIGHT, gimmeRoom().build())
                .addNextRoom(RoomSide.Direction.FRONT, gimmeRoom().build())
                .build();

        when(getRoomGateway.getById(room.getId())).thenReturn(Optional.of(room));

        // when
        EnterRoom.ResponseModel responseModel = usecase.enter(room.getId());

        // then
        assertThat(responseModel.getRoomId()).isEqualTo(room.getId());
        assertThat(responseModel.getRoomName()).isEqualTo(room.getName());

        assertThatEnterRoomResponse(responseModel).hasEnemiesOf(room);
        assertThatEnterRoomResponse(responseModel).hasItemsOf(room);
        assertThatEnterRoomResponse(responseModel).hasNextRoomsOf(room);

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
