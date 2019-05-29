package pihead.games.rpg.engine.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EnterRoom {

    ResponseModel enter(RequestModel requestModel);

    default ResponseModel enter(int roomId) {
        return enter(new RequestModel(roomId));
    }

    class RequestModel {
        private int roomId;

        public RequestModel(int roomId) {
            this.roomId = roomId;
        }

        public int getRoomId() {
            return roomId;
        }
    }

    class ResponseModel {

        private int roomId;
        private String roomName;
        private List<EnemyModel> enemies;
        private Map<DirectionModel, RoomSideModel> sides;

        private ResponseModel() {

        }

        public int getRoomId() {
            return roomId;
        }

        public String getRoomName() {
            return roomName;
        }

        public List<EnemyModel> getEnemies() {
            return enemies;
        }

        public Map<DirectionModel, RoomSideModel> getSides() {
            return sides;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {

            private ResponseModel instance;

            private Builder() {
                instance = new ResponseModel();
                instance.enemies = new ArrayList<>();
                instance.sides = new HashMap<>();
                instance.sides.put(DirectionModel.LEFT, new RoomSideModel());
                instance.sides.put(DirectionModel.RIGHT, new RoomSideModel());
                instance.sides.put(DirectionModel.FRONT, new RoomSideModel());
            }

            public Builder roomId(int roomId) {
                instance.roomId = roomId;
                return this;
            }

            public Builder roomName(String roomName) {
                instance.roomName = roomName;
                return this;
            }

            public Builder addEnemy(int id, String name, int damage, int health, int velocity) {
                instance.enemies.add(new EnemyModel(id, name, damage, health, velocity));
                return this;
            }

            public Builder addHealthItem(DirectionModel direction, int id, String name, int healthPower) {
                ItemModel itemModel = new ItemModel(id, name, ItemTypeModel.HEALTH, 0, healthPower);
                instance.sides.get(direction).items.add(itemModel);
                return this;
            }

            public Builder addWeapon(DirectionModel direction, int id, String name, int weaponDamage) {
                ItemModel itemModel = new ItemModel(id, name, ItemTypeModel.WEAPON, weaponDamage, 0);
                instance.sides.get(direction).items.add(itemModel);
                return this;
            }

            public Builder addNextRoom(DirectionModel direction, int id, String name) {
                instance.sides.get(direction).nextRoom = new NextRoomModel(id, name);
                return this;
            }

            public ResponseModel build() {
                return instance;
            }
        }

        public static class EnemyModel {
            private int id;
            private String name;
            private int damage;
            private int health;
            private int velocity;

            private EnemyModel(int id, String name, int damage, int health, int velocity) {
                this.id = id;
                this.name = name;
                this.damage = damage;
                this.health = health;
                this.velocity = velocity;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public int getDamage() {
                return damage;
            }

            public int getHealth() {
                return health;
            }

            public int getVelocity() {
                return velocity;
            }
        }

        public static class ItemModel {
            private int id;
            private String name;
            private ItemTypeModel type;
            private int weaponDamage;
            private int healthPower;

            private ItemModel(int id, String name, ItemTypeModel type, int weaponDamage, int healthPower) {
                this.id = id;
                this.name = name;
                this.type = type;
                this.weaponDamage = weaponDamage;
                this.healthPower = healthPower;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public ItemTypeModel getType() {
                return type;
            }

            public int getWeaponDamage() {
                return weaponDamage;
            }

            public int getHealthPower() {
                return healthPower;
            }
        }

        public static class NextRoomModel {
            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            private NextRoomModel(int id, String name) {
                this.id = id;
                this.name = name;
            }
        }

        public static class RoomSideModel {
            private List<ItemModel> items = new ArrayList<>();
            private NextRoomModel nextRoom;

            private RoomSideModel() {

            }

            public List<ItemModel> getItems() {
                return items;
            }

            public NextRoomModel getNextRoom() {
                return nextRoom;
            }

        }

        public enum DirectionModel {
            LEFT,
            RIGHT,
            FRONT
        }

        public enum ItemTypeModel {
            HEALTH,
            WEAPON
        }
    }

}
