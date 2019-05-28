package pihead.games.rpg.engine.domain;

public interface PlayerReceiveDamage {

    void receiveDamage(RequestModel requestModel);

    default void receiveDamage(int gameId, int playerId, int roomId) {
        receiveDamage(new RequestModel(gameId, playerId, roomId));
    }

    class RequestModel {
        private int gameId;
        private int playerId;
        private int roomId;

        public RequestModel(int gameId, int playerId, int roomId) {
            this.gameId = gameId;
            this.playerId = playerId;
            this.roomId = roomId;
        }

        public int getGameId() {
            return gameId;
        }

        public int getPlayerId() {
            return playerId;
        }

        public int getRoomId() {
            return roomId;
        }
    }

}
