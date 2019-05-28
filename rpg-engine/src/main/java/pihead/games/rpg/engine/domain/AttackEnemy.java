package pihead.games.rpg.engine.domain;

public interface AttackEnemy {

    void attack(RequestModel requestModel);

    default void attack(int weaponId, int enemyId) {
        attack(new RequestModel(weaponId, enemyId));
    }

    class RequestModel {
        private int weaponId;
        private int enemyId;

        public RequestModel(int weaponId, int enemyId) {
            this.weaponId = weaponId;
            this.enemyId = enemyId;
        }

        public int getWeaponId() {
            return weaponId;
        }

        public int getEnemyId() {
            return enemyId;
        }
    }

}
