package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.Room;
import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.domain.entities.items.Weapon;

public interface AttackOnRoom {

    ResponseModel execute(RequestModel requestModel);

    class RequestModel {
        private Room room;
        private Player player;
        private Weapon weapon;
        private Enemy enemy;

        public Room getRoom() {
            return room;
        }

        public Player getPlayer() {
            return player;
        }

        public Weapon getWeapon() {
            return weapon;
        }

        public Enemy getEnemy() {
            return enemy;
        }
    }

    class ResponseModel {
        private Room room;

        public Room getRoom() {
            return room;
        }
    }
}
