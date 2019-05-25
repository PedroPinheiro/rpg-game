package pihead.games.rpg.engine.domain.entities.items;

public class HealthItem implements Item {

    private HealthItemType type;

    public HealthItem() {

    }

    @Override
    public HealthItemType getType() {
        return type;
    }

    // Builder

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        protected HealthItem instance;

        protected Builder() {
            instance = new HealthItem();
        }

        public Builder type(HealthItemType type) {
            instance.type = type;
            return this;
        }

        public HealthItem build() {
            return instance;
        }
    }
}
