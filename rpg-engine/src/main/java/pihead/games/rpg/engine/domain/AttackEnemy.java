package pihead.games.rpg.engine.domain;

public interface AttackEnemy {

    ResponseModel attack(RequestModel requestModel);

    default ResponseModel attack(int weaponId, int enemyId) {
        return attack(new RequestModel(weaponId, enemyId));
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

    class ResponseModel {
        private boolean success;

        private ResponseModel() {

        }

        public boolean isSuccess() {
            return success;
        }

        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {

            private ResponseModel model;

            private Builder() {
                model = new ResponseModel();
            }

            public Builder success(boolean success) {
                model.success = success;
                return this;
            }

            public ResponseModel build() {
                return model;
            }
        }

    }
}
