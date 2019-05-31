package pihead.games.rpg.engine.domain;

import java.util.ArrayList;
import java.util.List;

public interface ListGameTypes {

    ResponseModel list();

    class ResponseModel {
        private List<GameTypeModel> gameTypes = new ArrayList<>();

        private ResponseModel() {

        }

        public List<GameTypeModel> getGameTypes() {
            return gameTypes;
        }

        // Builder

        public static Builder builder() {
            return new Builder();
        }

        static class Builder {
            private ResponseModel instance;

            Builder() {
                instance = new ResponseModel();
            }

            public Builder addGameType(int id, String name, String description) {
                instance.gameTypes.add(new GameTypeModel(id, name, description));
                return this;
            }

            public ResponseModel build() {
                return instance;
            }
        }


        public static class GameTypeModel {
            private int id;
            private String name;
            private String description;

            private GameTypeModel(int id, String name, String description) {
                this.id = id;
                this.name = name;
                this.description = description;
            }

            public int getId() {
                return id;
            }

            public String getName() {
                return name;
            }

            public String getDescription() {
                return description;
            }
        }
    }
}
