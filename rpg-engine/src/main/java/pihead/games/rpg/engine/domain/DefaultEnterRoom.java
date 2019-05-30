package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.Room;
import pihead.games.rpg.engine.domain.entities.RoomSide;
import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.items.HealthItem;
import pihead.games.rpg.engine.domain.entities.items.Weapon;
import pihead.games.rpg.engine.gateway.GetRoomGateway;
import pihead.games.rpg.engine.domain.EnterRoom.ResponseModel.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class DefaultEnterRoom implements EnterRoom {

    private GetRoomGateway getRoomGateway;

    public DefaultEnterRoom(GetRoomGateway getRoomGateway) {
        this.getRoomGateway = getRoomGateway;
    }

    @Override
    public ResponseModel enter(RequestModel requestModel) {

        Room room = getRoom(requestModel);

        return buildResponse(room);
    }

    private Room getRoom(RequestModel requestModel) {
        return getRoomGateway.getById(requestModel.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    private ResponseModel buildResponse(Room room) {

        ResponseModel.Builder builder = ResponseModel.builder()
                .roomId(room.getId())
                .roomName(room.getName());

        room.getEnemies().stream()
                .filter(Enemy::isAlive)
                .forEach(addEnemyToResponse(builder));

        room.getSides()
                .forEach(addSideToResponse(builder));

        return builder.build();
    }

    private Consumer<Enemy> addEnemyToResponse(Builder builder) {
        return (enemy) ->
            builder.addEnemy(enemy.getId(),
                    enemy.getType().getName(),
                    enemy.getType().getDamage(),
                    enemy.getHealth(),
                    enemy.getType().getVelocity());
    }

    private BiConsumer<RoomSide.Direction, RoomSide> addSideToResponse(Builder builder) {
        return (direction, side) -> {

            DirectionModel modelDirection = getSideDirection(direction);
            addItemsToResponse(builder, side, modelDirection);
            addNextRoomsToResponse(builder, side, modelDirection);
        };
    }

    private void addItemsToResponse(Builder builder,
                                    RoomSide side,
                                    DirectionModel directionModel) {

        if (side.getItems().size()>0) {
            side.getItems().forEach(item -> {

                int itemId = item.getId();
                String itemName = item.getType().getName();

                if (item instanceof HealthItem) {
                    HealthItem healthItem = (HealthItem) item;
                    builder.addHealthItem(directionModel, itemId, itemName, healthItem.getType().getHealthPower());
                } else {
                    Weapon weapon = (Weapon) item;
                    builder.addWeapon(directionModel, itemId, itemName, weapon.getType().getDamage());
                }
            });
        }
    }

    private void addNextRoomsToResponse(Builder builder,
                                        RoomSide side,
                                        DirectionModel directionModel) {

        if (side.getNextRoom() != null) {
            int nextRoomId = side.getNextRoom().getId();
            String nextRoomName = side.getNextRoom().getName();
            builder.addNextRoom(directionModel, nextRoomId, nextRoomName);
        }
    }

    private DirectionModel getSideDirection(RoomSide.Direction direction) {

        DirectionModel result;
        
        if (direction == RoomSide.Direction.LEFT) {
            result = DirectionModel.LEFT;
        } else if (direction == RoomSide.Direction.RIGHT) {
            result = DirectionModel.RIGHT;
        } else {
            result = DirectionModel.FRONT;
        }

        return result;
    }
}
