package pihead.games.rpg.engine.domain.entities.characters;

public abstract class Character<T extends CharacterType> {

    protected Integer health;
    private T type;

    protected Character() {

    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void receiveDamage(int damage) {
        this.health = Math.max(this.health-damage, 0);
    }

    public abstract T getType();
}
