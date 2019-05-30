package pihead.games.rpg.engine.domain.entities.backpack;

import pihead.games.rpg.engine.domain.entities.backpack.exception.BackpackIsFullException;
import pihead.games.rpg.engine.domain.entities.backpack.exception.ItemWillNotFitInBackpackException;
import pihead.games.rpg.engine.domain.entities.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Backpack {

    private Integer id;
    private List<Item> items;
    private Integer availableSlots;
    private Integer maxSlots;

    public Backpack() {
        items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public int getMaxSlots() {
        return maxSlots;
    }

    public void addItem(Item item) throws BackpackIsFullException, ItemWillNotFitInBackpackException {
        if (isFull()) {
            throw new BackpackIsFullException();
        }
        if (itemWillNotFit(item)) {
            throw new ItemWillNotFitInBackpackException(item);
        }
        availableSlots -= item.getType().getSlots();
        items.add(item);
    }

    public void dropItem(Item item) {
        availableSlots += item.getType().getSlots();
        items.remove(item);
    }

    private boolean isFull() {
        return availableSlots == 0;
    }

    private boolean itemWillNotFit(Item item) {
        return availableSlots - item.getType().getSlots() < 0;
    }

    // Builder

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        protected Backpack instance;

        protected Builder() {
            instance = new Backpack();
        }

        public Builder id(int id) {
            instance.id = id;
            return this;
        }

        public Builder maxSlots(int maxSlots) {
            instance.maxSlots = maxSlots;
            return this;
        }

        public Backpack build() {
            if (instance.id == null) {
                throw new IllegalArgumentException("Backpack Id could not be null.");
            }
            if (instance.maxSlots == null) {
                throw new IllegalArgumentException("Backpack max slots could not be null.");
            }
            instance.availableSlots = instance.maxSlots;
            return instance;
        }
    }
}
