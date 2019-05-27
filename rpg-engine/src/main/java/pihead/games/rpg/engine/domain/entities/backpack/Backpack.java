package pihead.games.rpg.engine.domain.entities.backpack;

import pihead.games.rpg.engine.domain.entities.backpack.exception.BackpackIsFullException;
import pihead.games.rpg.engine.domain.entities.backpack.exception.ItemWillNotFitInBackpackException;
import pihead.games.rpg.engine.domain.entities.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Backpack {

    private List<Item> items;
    private int availableSlots;
    private int maxSlots;

    public Backpack(int maxSlots) {
        items = new ArrayList<>();
        this.availableSlots = maxSlots;
        this.maxSlots = maxSlots;
    }

    public int getAvailableSlots() {
        return availableSlots;
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
}
