package pihead.games.rpg.engine.domain.entities.items;

public abstract class ItemType {

    protected String name;
    protected int slots;

    protected ItemType() {

    }

    public String getName() {
        return name;
    }

    public int getSlots() {
        return slots;
    }
}
