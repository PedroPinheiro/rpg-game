package pihead.games.rpg.engine.gateway;

import pihead.games.rpg.engine.domain.entities.Room;

import java.util.Optional;

public interface GetRoomGateway {

    Optional<Room> getById(int roomId);
}
