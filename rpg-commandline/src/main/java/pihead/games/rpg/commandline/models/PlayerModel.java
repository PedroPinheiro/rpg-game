package pihead.games.rpg.commandline.models;

public class PlayerModel implements Model {

    private int id;
    private String name;
    private int playerTypeId;
    private String playerTypeName;

    public PlayerModel() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPlayerTypeId() {
        return playerTypeId;
    }

    public String getPlayerTypeName() {
        return playerTypeName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerTypeId(int playerTypeId) {
        this.playerTypeId = playerTypeId;
    }

    public void setPlayerTypeName(String playerTypeName) {
        this.playerTypeName = playerTypeName;
    }
}
