package pihead.games.rpg.engine.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EnterRoom {

    ResponseModel enter(RequestModel requestModel);


    class RequestModel {
        private int gameId;
        private int roomId;

        public RequestModel(int gameId, int roomId) {
            this.gameId = gameId;
            this.roomId = roomId;
        }

        public int getGameId() {
            return gameId;
        }

        public int getRoomId() {
            return roomId;
        }
    }

    class ResponseModel {

        private int roomId;
        private String roomName;
        private List<EnemyModel> enemies;
        private Map<RoomSideModel, List<ItemModel>> items;
        private Map<RoomSideModel, NextRoomModel> nextRooms;

        private ResponseModel() {

        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {

            private ResponseModel instance;

            private Builder() {
                instance = new ResponseModel();
                instance.enemies = new ArrayList<>();
                instance.items = new HashMap<>();
                instance.items.put(RoomSideModel.LEFT, new ArrayList<>());
                instance.items.put(RoomSideModel.RIGHT, new ArrayList<>());
                instance.items.put(RoomSideModel.FRONT, new ArrayList<>());
                instance.nextRooms = new HashMap<>();
            }

            public Builder roomId(int roomId) {
                instance.roomId = roomId;
                return this;
            }

            public Builder roomName(String roomName) {
                instance.roomName = roomName;
                return this;
            }

            public Builder addEnemy(int id, String name, int damage, int health) {
                instance.enemies.add(new EnemyModel(id, name, damage, health));
                return this;
            }

            public Builder addHealthItem(RoomSideModel side, int id, String name, int healthPower) {
                ItemModel itemModel = new ItemModel(id, name, ItemTypeModel.HEALTH, 0, healthPower);
                instance.items.get(side).add(itemModel);
                return this;
            }

            public Builder addWeapon(RoomSideModel side, int id, String name, int weaponDamage) {
                ItemModel itemModel = new ItemModel(id, name, ItemTypeModel.WEAPON, weaponDamage, 0);
                instance.items.get(side).add(itemModel);
                return this;
            }

            public Builder addNextRoom(RoomSideModel side, int id, String name) {
                instance.nextRooms.put(RoomSideModel.LEFT, new NextRoomModel(id, name));
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

            private EnemyModel(int id, String name, int damage, int health) {
                this.id = id;
                this.name = name;
                this.damage = damage;
                this.health = health;
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
        }

        public static class NextRoomModel {
            private int id;
            private String name;

            private NextRoomModel(int id, String name) {
                this.id = id;
                this.name = name;
            }
        }

        public static enum RoomSideModel {
            LEFT,
            RIGHT,
            FRONT
        }

        public static enum ItemTypeModel {
            HEALTH,
            WEAPON
        }
    }

}
