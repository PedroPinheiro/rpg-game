package domain.entities;

public abstract class Character {

    protected int health;

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void receiveDamage(int damage) {
        this.health = Math.max(this.health-damage, 0);
    }
}
