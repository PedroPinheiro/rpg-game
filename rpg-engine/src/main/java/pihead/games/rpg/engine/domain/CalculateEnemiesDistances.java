package pihead.games.rpg.engine.domain;

public interface CalculateEnemiesDistances {

    void calculate(RequestModel requestModel);

    default void calculate(int roomId) {
        calculate(new RequestModel(roomId));
    }

    class RequestModel {

        private int roomId;

        public RequestModel(int roomId) {
            this.roomId = roomId;
        }

        public int getRoomId() {
            return roomId;
        }

    }

}
