package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.Room;
import pihead.games.rpg.engine.domain.entities.characters.Character;
import pihead.games.rpg.engine.domain.entities.characters.Enemy;
import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.domain.entities.items.Weapon;

public class DefaultAttackOnRoom implements AttackOnRoom {

    private AttackEnemy attackEnemy;

    public DefaultAttackOnRoom(AttackEnemy attackEnemy) {
        this.attackEnemy = attackEnemy;
    }

    @Override
    public ResponseModel execute(RequestModel requestModel) {

        Room room = requestModel.getRoom();
        Player player = requestModel.getPlayer();
        Weapon weapon = requestModel.getWeapon();
        Enemy enemy = requestModel.getEnemy();

//        attackEnemy.attack(player, weapon, enemy);

        progressEnemies(room);

        return null;
    }

    private void progressEnemies(Room room) {
        room.getEnemies().stream()
                .filter(Character::isAlive)
                .forEach(Enemy::progressDistance);
    }
}
