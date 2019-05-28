package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.Room;
import pihead.games.rpg.engine.domain.entities.RoomSide;
import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.items.HealthItem;
import pihead.games.rpg.engine.domain.entities.items.Weapon;
import pihead.games.rpg.engine.gateway.GetRoomGateway;

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

    private Consumer<Enemy> addEnemyToResponse(ResponseModel.Builder builder) {
        return (enemy) ->
            builder.addEnemy(enemy.getId(),
                    enemy.getType().getName(),
                    enemy.getType().getDamage(),
                    enemy.getHealth());
    }

    private BiConsumer<RoomSide.Direction, RoomSide> addSideToResponse(ResponseModel.Builder builder) {
        return (direction, side) -> {
            ResponseModel.RoomSideModel modelSide = getSideModel(direction);
            addItemsToResponse(builder, side, modelSide);
            addNextRoomsToResponse(builder, side, modelSide);
        };
    }

    private void addItemsToResponse(ResponseModel.Builder builder,
                                    RoomSide side,
                                    ResponseModel.RoomSideModel modelSide) {

        if (side.getItems().size()>0) {
            side.getItems().forEach(item -> {

                int itemId = item.getId();
                String itemName = item.getType().getName();

                if (item instanceof HealthItem) {
                    HealthItem healthItem = (HealthItem) item;
                    builder.addHealthItem(modelSide, itemId, itemName, healthItem.getType().getHealthPower());
                } else {
                    Weapon weapon = (Weapon) item;
                    builder.addWeapon(modelSide, itemId, itemName, weapon.getType().getDamage());
                }
            });
        }
    }

    private void addNextRoomsToResponse(ResponseModel.Builder builder,
                                        RoomSide side,
                                        ResponseModel.RoomSideModel modelSide) {

        if (side.getNextRoom() != null) {
            int nextRoomId = side.getNextRoom().getId();
            String nextRoomName = side.getNextRoom().getName();
            builder.addNextRoom(modelSide, nextRoomId, nextRoomName);
        }
    }

    private ResponseModel.RoomSideModel getSideModel(RoomSide.Direction direction) {

        ResponseModel.RoomSideModel result = ResponseModel.RoomSideModel.FRONT;
        switch (direction) {
            case LEFT:
                result = ResponseModel.RoomSideModel.LEFT;
                break;
            case RIGHT:
                result = ResponseModel.RoomSideModel.RIGHT;
                break;
            case FRONT:
                result = ResponseModel.RoomSideModel.FRONT;
                break;
        }

        return result;
    }
}
