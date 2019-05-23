package domain.entities.backpack;

import domain.entities.items.Item;

public class ItemWillNotFitInBackpackException extends Exception {

    private Item item;

    public ItemWillNotFitInBackpackException(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
