package pihead.games.rpg.engine.domain;

import java.util.ArrayList;
import java.util.List;

public interface OpenBackpack {

    ResponseModel openBackpack(RequestModel requestModel);

    default ResponseModel openBackpack(int playerId) {
        return openBackpack(new RequestModel(playerId));
    }

    class RequestModel {
        private int playerId;

        private RequestModel(int playerId) {
            this.playerId = playerId;
        }

        public int getPlayerId() {
            return playerId;
        }
    }

    class ResponseModel {
        private int backpackId;
        private List<ItemModel> items;
        private int availableSlots;
        private int maxSlots;

        private ResponseModel() {

        }

        public int getBackpackId() {
            return backpackId;
        }

        public List<ItemModel> getItems() {
            return items;
        }

        public int getAvailableSlots() {
            return availableSlots;
        }

        public int getMaxSlots() {
            return maxSlots;
        }

        // Builder

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {

            private ResponseModel instance;

            private Builder() {
                instance = new ResponseModel();
                instance.items = new ArrayList<>();
            }

            public Builder backpackId(int backpackId) {
                instance.backpackId = backpackId;
                return this;
            }

            public Builder addHealthItem(int id, String name, int healthPower) {
                ItemModel itemModel = new ItemModel(id, name, ItemTypeModel.HEALTH, 0, healthPower);
                instance.items.add(itemModel);
                return this;
            }

            public Builder addWeapon(int id, String name, int weaponDamage) {
                ItemModel itemModel = new ItemModel(id, name, ItemTypeModel.WEAPON, weaponDamage,0);
                instance.items.add(itemModel);
                return this;
            }

            public Builder availableSlots(int availableSlots) {
                instance.availableSlots = availableSlots;
                return this;
            }

            public Builder maxSlots(int maxSlots) {
                instance.maxSlots = maxSlots;
                return this;
            }

            public ResponseModel build() {
                return instance;
            }
        }

        // inner classes

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

        public enum ItemTypeModel {
            HEALTH,
            WEAPON
        }


    }
}
