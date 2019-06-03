package pihead.games.rpg.engine.domain;

import java.util.ArrayList;
import java.util.List;

public interface ListPlayerTypes {

    ResponseModel list(RequestModel requestModel);

    default ResponseModel list(String gameTypeId) {
        return list(new RequestModel(gameTypeId));
    }

    class RequestModel {
        private String gameTypeId;

        private RequestModel(String gameTypeId) {
            this.gameTypeId = gameTypeId;
        }

        public String getGameTypeId() {
            return gameTypeId;
        }
    }

    class ResponseModel {

        private List<PlayerTypeModel> playerTypes = new ArrayList<>();

        private ResponseModel() {
        }

        public List<PlayerTypeModel> getPlayerTypes() {
            return playerTypes;
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

            public Builder addPlayerType(String name, int maxHealth) {
                PlayerTypeModel playerTypeModel = new PlayerTypeModel(name, maxHealth);
                instance.playerTypes.add(playerTypeModel);
                return this;
            }

            public ResponseModel build() {
                return instance;
            }
        }

        static class PlayerTypeModel {
            private String name;
            private int maxHealth;

            private PlayerTypeModel(String name, int maxHealth) {
                this.name = name;
                this.maxHealth = maxHealth;
            }

            public String getName() {
                return name;
            }

            public int getMaxHealth() {
                return maxHealth;
            }
        }

    }

}
