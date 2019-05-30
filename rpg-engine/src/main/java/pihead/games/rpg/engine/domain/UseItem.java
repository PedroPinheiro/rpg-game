package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.backpack.exception.BackpackIsFullException;
import pihead.games.rpg.engine.domain.entities.backpack.exception.ItemWillNotFitInBackpackException;

public interface UseItem {

    void useItem(RequestModel requestModel) throws BackpackIsFullException, ItemWillNotFitInBackpackException;

    default void useItem(int playerId, int itemId) throws BackpackIsFullException, ItemWillNotFitInBackpackException {
        useItem(new RequestModel(playerId, itemId));
    }

    class RequestModel {
        private int playerId;
        private int itemId;

        private RequestModel(int playerId, int itemId) {
            this.playerId = playerId;
            this.itemId = itemId;
        }

        public int getPlayerId() {
            return playerId;
        }

        public int getItemId() {
            return itemId;
        }
    }
}
