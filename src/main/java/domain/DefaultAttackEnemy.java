package domain;

import domain.entities.Enemy;
import domain.entities.Weapon;
import domain.entities.Player;

public class DefaultAttackEnemy implements AttackEnemy {

    @Override
    public ResponseModel attack(RequestModel requestModel) {

        Player player = requestModel.getPlayer();
        Enemy enemy = requestModel.getEnemy();
        Weapon weapon = requestModel.getWeapon();

        player.receiveDamage(enemy.getType().getDamage());
        enemy.receiveDamage(weapon.getType().getDamage());
        weapon.shoot();

        return mountResponse(player, weapon, enemy);
    }

    private ResponseModel mountResponse(Player player, Weapon weapon, Enemy enemy) {

        return ResponseModel.builder()
                .player(player)
                .enemy(enemy)
                .weapon(weapon)
                .build();
    }
}
