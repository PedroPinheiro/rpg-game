package domain.entities.backpack;

import domain.entities.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Backpack {

    private List<Item> items;
    private int availableSlots = 20;

    public Backpack() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) throws BackpackIsFullException {
        if (!canAddItem(item)) {
            throw new BackpackIsFullException();
        }
        availableSlots -= item.getType().getSlots();
        items.add(item);
    }

    public void dropItem(Item item) {
        availableSlots += item.getType().getSlots();
        items.remove(item);
    }

    private boolean canAddItem(Item item) {
        return availableSlots - item.getType().getSlots() > 0;
    }
}
