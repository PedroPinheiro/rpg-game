package pihead.games.rpg.engine.domain.entities.items;

public interface Item<T extends ItemType> {

    int getId();
    T getType();
}
