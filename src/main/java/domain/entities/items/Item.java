package domain.entities.items;

public interface Item<T extends ItemType> {

    T getType();
}
