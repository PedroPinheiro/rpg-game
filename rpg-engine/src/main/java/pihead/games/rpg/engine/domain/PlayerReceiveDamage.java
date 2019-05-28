package pihead.games.rpg.engine.domain;

public interface PlayerReceiveDamage {

    void receiveDamage(RequestModel requestModel);

    default void receiveDamage(int playerId, int roomId) {
        receiveDamage(new RequestModel(playerId, roomId));
    }

    class RequestModel {
        private int playerId;
        private int roomId;

        public RequestModel(int playerId, int roomId) {
            this.playerId = playerId;
            this.roomId = roomId;
        }

        public int getPlayerId() {
            return playerId;
        }

        public int getRoomId() {
            return roomId;
        }
    }

}
