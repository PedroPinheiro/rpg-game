package domain.entities;

public interface Item<T extends ItemType> {

    T getType();
}
