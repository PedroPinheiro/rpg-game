package pihead.games.rpg.commandline.models;

public class PlayerTypeModel implements OptionModel {

    private Integer id;
    private String name;
    private int maxHealth;

    public PlayerTypeModel(Integer id, String name, int maxHealth) {
        this.id = id;
        this.name = name;
        this.maxHealth = maxHealth;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }
}
