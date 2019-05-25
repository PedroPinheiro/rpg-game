package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.items.Weapon;
import pihead.games.rpg.engine.domain.entities.characters.Player;

public interface AttackEnemy {

    ResponseModel attack(RequestModel requestModel);

    default ResponseModel attack(Player player, Weapon weapon, Enemy enemy) {
        return attack(new RequestModel(player, weapon, enemy));
    }

    class RequestModel {
        private Player player;
        private Enemy enemy;
        private Weapon weapon;

        private RequestModel(Player player, Weapon weapon, Enemy enemy) {
            this.player = player;
            this.weapon = weapon;
            this.enemy = enemy;
        }

        public Player getPlayer() {
            return player;
        }

        public Enemy getEnemy() {
            return enemy;
        }

        public Weapon getWeapon() {
            return weapon;
        }

    }

    class ResponseModel {
        private Player player;
        private Enemy enemy;
        private Weapon weapon;

        private ResponseModel() {

        }

        public Player getPlayer() {
            return player;
        }

        public Enemy getEnemy() {
            return enemy;
        }

        public Weapon getWeapon() {
            return weapon;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {

            private ResponseModel model;

            private Builder() {
                model = new ResponseModel();
            }

            public Builder player(Player player) {
                model.player = player;
                return this;
            }

            public Builder enemy(Enemy enemy) {
                model.enemy = enemy;
                return this;
            }

            public Builder weapon(Weapon weapon) {
                model.weapon = weapon;
                return this;
            }

            public ResponseModel build() {
                return model;
            }
        }

    }
}
