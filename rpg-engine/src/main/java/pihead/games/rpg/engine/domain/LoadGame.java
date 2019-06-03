package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.loader.GameLoader;

public interface LoadGame {

    ResponseModel load(RequestModel requestModel);

    default ResponseModel load(String gameTypeId, int playerTypeId, String playerName) {
        return load(new RequestModel(gameTypeId, playerTypeId, playerName));
    }


    class RequestModel {
        private String gameTypeId;
        private int playerTypeId;
        private String playerName;

        public RequestModel(String gameTypeId, int playerTypeId, String playerName) {
            this.gameTypeId = gameTypeId;
            this.playerTypeId = playerTypeId;
            this.playerName = playerName;
        }

        public String getGameTypeId() {
            return gameTypeId;
        }

        public int getPlayerTypeId() {
            return playerTypeId;
        }

        public String getPlayerName() {
            return playerName;
        }

    }

    class ResponseModel {

        private int gameId;

        private ResponseModel() {

        }

        public int getGameId() {
            return gameId;
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

            public Builder gameId(int gameId) {
                instance.gameId = gameId;
                return this;
            }

            public ResponseModel build() {
                return instance;
            }
        }
    }

}
