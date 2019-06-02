package pihead.games.rpg.engine.domain.entities;

public class Stage {

    private Integer id;
    private String name;
    private Room firstRoom;
    public Stage next;

    private Stage() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Room getFirstRoom() {
        return firstRoom;
    }

    public Stage getNext() {
        return next;
    }

    // Builder

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Stage instance;

        private Builder() {
            instance = new Stage();
        }

        public Builder id(int id) {
            instance.id = id;
            return this;
        }

        public Builder name(String name) {
            instance.name = name;
            return this;
        }

        public Builder addNextStage(Stage nextStage) {
            instance.next = nextStage;
            return this;
        }

        public Builder addFirstRoom(Room firstRoom) {
            instance.firstRoom = firstRoom;
            return this;
        }


        public Stage build() {
            if (instance.firstRoom == null) {
                throw new IllegalArgumentException("First Room of Stage could not be null.");
            }

            return instance;
        }
    }

}
