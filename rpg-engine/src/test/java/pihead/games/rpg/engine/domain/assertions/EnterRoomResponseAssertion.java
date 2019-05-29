package pihead.games.rpg.engine.domain.assertions;

import org.assertj.core.api.ListAssert;
import pihead.games.rpg.engine.domain.EnterRoom.ResponseModel;
import pihead.games.rpg.engine.domain.EnterRoom.ResponseModel.*;
import pihead.games.rpg.engine.domain.entities.Room;
import pihead.games.rpg.engine.domain.entities.RoomSide;
import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.items.HealthItem;
import pihead.games.rpg.engine.domain.entities.items.Weapon;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class EnterRoomResponseAssertion {

    public static EnemiesAssert assertThatEnterRoomResponse(ResponseModel responseModel) {
        return new EnemiesAssert(responseModel);
    }

    public static class EnemiesAssert {

        ResponseModel responseModel;

        private EnemiesAssert(ResponseModel responseModel) {
            this.responseModel = responseModel;
        }

        public void hasEnemiesOf(Room room) {

            ListAssert<EnemyModel> enemiesAssert = assertThat(responseModel.getEnemies());

            enemiesAssert.hasSameClassAs(room.getEnemies());

            Integer[] ids = room.getEnemies().stream().map(Enemy::getId).toArray(Integer[]::new);
            String[] names = room.getEnemies().stream().map(e -> e.getType().getName()).toArray(String[]::new);
            Integer[] damages = room.getEnemies().stream().map(e -> e.getType().getDamage()).toArray(Integer[]::new);
            Integer[] health = room.getEnemies().stream().map(Enemy::getHealth).toArray(Integer[]::new);
            Integer[] velocity = room.getEnemies().stream().map(e -> e.getType().getVelocity()).toArray(Integer[]::new);

            enemiesAssert.extracting(EnemyModel::getId).containsExactlyInAnyOrder(ids);
            enemiesAssert.extracting(EnemyModel::getName).containsExactlyInAnyOrder(names);
            enemiesAssert.extracting(EnemyModel::getDamage).containsExactlyInAnyOrder(damages);
            enemiesAssert.extracting(EnemyModel::getHealth).containsExactlyInAnyOrder(health);
            enemiesAssert.extracting(EnemyModel::getVelocity).containsExactlyInAnyOrder(velocity);

        }

        public void hasItemsOf(Room room) {

            room.getSides()
                    .forEach((direction,  side) -> {

                        DirectionModel directionModel = toDirectionModel(direction);

                        side.getItems().forEach(item -> {

                            Optional<ItemModel> optionalItemModel = responseModel.getSides()
                                    .get(directionModel)
                                    .getItems()
                                    .stream()
                                    .filter(im -> im.getId() == item.getId()).findFirst();

                            assertThat(optionalItemModel.isPresent()).isTrue();

                            ItemModel itemModel = optionalItemModel.get();
                            assertThat(itemModel.getName()).isEqualTo(item.getType().getName());

                            if (item instanceof HealthItem) {
                                HealthItem healthItem = (HealthItem) item;
                                assertThat(itemModel.getType()).isEqualTo(ItemTypeModel.HEALTH);
                                assertThat(itemModel.getHealthPower()).isEqualTo(healthItem.getType().getHealthPower());
                                assertThat(itemModel.getWeaponDamage()).isEqualTo(0);
                            } else if (item instanceof Weapon) {
                                Weapon weapon = (Weapon) item;
                                assertThat(itemModel.getType()).isEqualTo(ItemTypeModel.WEAPON);
                                assertThat(itemModel.getHealthPower()).isEqualTo(0);
                                assertThat(itemModel.getWeaponDamage()).isEqualTo(weapon.getType().getDamage());
                            }
                        });
            });

        }

        public void hasNextRoomsOf(Room room) {

            room.getSides()
                    .forEach((direction,  side) -> {

                        DirectionModel directionModel = toDirectionModel(direction);
                        Room nextRoom = side.getNextRoom();
                        NextRoomModel nextRoomModel = responseModel.getSides().get(directionModel).getNextRoom();

                        if (nextRoom == null) {
                            assertThat(nextRoomModel).isNull();
                        } else {
                            assertThat(nextRoomModel).isNotNull();
                            assertThat(nextRoomModel.getId()).isEqualTo(nextRoom.getId());
                            assertThat(nextRoomModel.getName()).isEqualTo(nextRoom.getName());
                        }

                    });
        }

        private DirectionModel toDirectionModel(RoomSide.Direction direction) {

            DirectionModel result = DirectionModel.FRONT;
            switch (direction) {
                case LEFT:
                    result = DirectionModel.LEFT;
                    break;
                case RIGHT:
                    result = DirectionModel.RIGHT;
                    break;
            }

            return result;
        }
    }

}