package domain.entities;

public class WeaponType implements ItemType {

    private String name;
    private int damage;
    private int range;
    private int shots;
    private int slots;

    public WeaponType(String name, int damage, int range, int shots, int slots) {
        this.name = name;
        this.damage = damage;
        this.range = range;
        this.shots = shots;
        this.slots = slots;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getDistance() {
        return range;
    }

    public int getShots() {
        return shots;
    }

    @Override
    public int getSlots() {
        return slots;
    }

    public boolean isFireGun() {
        return shots > 0;
    }
}
