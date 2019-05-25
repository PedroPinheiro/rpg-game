package pihead.games.rpg.engine.domain.entities.items;

public interface Item<T extends ItemType> {

    T getType();
}
