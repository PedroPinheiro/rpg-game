package domain.entities.items;

public class HealthItem implements Item {

    private HealthItemType type;

    public HealthItem(HealthItemType type) {
        this.type = type;
    }

    @Override
    public HealthItemType getType() {
        return type;
    }
}
