package pihead.games.rpg.engine.domain.entities.characters;

public abstract class CharacterType {

    protected String name;
    protected int maxHealth;
    protected int damage;
    protected int minAttackDistance;

    protected CharacterType() {

    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public int getMinAttackDistance() {
        return minAttackDistance;
    }

}
