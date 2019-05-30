package pihead.games.rpg.engine.domain;

public interface UseHealthItem {

    void useItem(RequestModel requestModel);

    default void useItem(int playerId, int healthItemId) {
        useItem(new RequestModel(playerId, healthItemId));
    }

    class RequestModel {
        private int playerId;
        private int healthItemId;

        private RequestModel(int playerId, int healthItemId) {
            this.playerId = playerId;
            this.healthItemId = healthItemId;
        }

        public int getPlayerId() {
            return playerId;
        }

        public int getHealthItemId() {
            return healthItemId;
        }
    }
}
