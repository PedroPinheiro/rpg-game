package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.exceptions.PlayerIsNotCarringWeapon;

public interface AttackOnRoom {

    void attack(RequestModel requestModel) throws PlayerIsNotCarringWeapon;

    default void attack(int gameId, int roomId, int playerId, int chosenEnemyId) throws PlayerIsNotCarringWeapon {
        attack(new RequestModel(gameId, roomId, playerId, chosenEnemyId));
    }

    class RequestModel {
        private int gameId;
        private int roomId;
        private int playerId;
        private int chosenEnemyId;

        public RequestModel(int gameId, int roomId, int playerId, int chosenEnemyId) {
            this.gameId = gameId;
            this.roomId = roomId;
            this.playerId = playerId;
            this.chosenEnemyId = chosenEnemyId;
        }

        public int getGameId() {
            return gameId;
        }

        public int getRoomId() {
            return roomId;
        }

        public int getPlayerId() {
            return playerId;
        }

        public int getChosenEnemyId() {
            return chosenEnemyId;
        }

    }

}
