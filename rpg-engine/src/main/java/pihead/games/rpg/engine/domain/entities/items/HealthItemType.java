package pihead.games.rpg.engine.domain.entities.items;

public class HealthItemType extends ItemType {

    private int healthPower;

    public int getHealthPower() {
        return healthPower;
    }


    // Builder

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        protected HealthItemType instance;

        protected Builder() {
            instance = new HealthItemType();
        }

        public Builder name(String name) {
            instance.name = name;
            return this;
        }

        public Builder slots(int slots) {
            instance.slots = slots;
            return this;
        }

        public Builder healthPower(int healthPower) {
            instance.healthPower = healthPower;
            return this;
        }

        public HealthItemType build() {
            return instance;
        }
    }
}
