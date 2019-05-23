package domain.entities;

public class WeaponType {

    private String name;
    private int damage;
    private int distance;
    private int shots;

    public WeaponType(String name, int damage, int distance, int shots) {
        this.name = name;
        this.damage = damage;
        this.distance = distance;
        this.shots = shots;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getDistance() {
        return distance;
    }

    public int getShots() {
        return shots;
    }

    public boolean isFireGun() {
        return shots > 0;
    }
}
