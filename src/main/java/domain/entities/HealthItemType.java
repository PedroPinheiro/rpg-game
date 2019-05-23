package domain.entities;

public class HealthItemType implements ItemType {

    private String name;
    private int slots;
    private int power;

    public HealthItemType(String name, int slots, int power) {
        this.name = name;
        this.slots = slots;
        this.power = power;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getSlots() {
        return slots;
    }

    public int getPower() {
        return power;
    }
}
