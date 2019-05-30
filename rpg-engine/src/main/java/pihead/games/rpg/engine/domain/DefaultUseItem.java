package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.backpack.Backpack;
import pihead.games.rpg.engine.domain.entities.backpack.exception.BackpackIsFullException;
import pihead.games.rpg.engine.domain.entities.backpack.exception.ItemWillNotFitInBackpackException;
import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.domain.entities.items.HealthItem;
import pihead.games.rpg.engine.domain.entities.items.Item;
import pihead.games.rpg.engine.domain.entities.items.Weapon;
import pihead.games.rpg.engine.gateway.GetPlayerGateway;
import pihead.games.rpg.engine.gateway.UpdateBackpackGateway;
import pihead.games.rpg.engine.gateway.UpdatePlayerGateway;

public class DefaultUseItem implements UseItem {

    private GetPlayerGateway getPlayerGateway;
    private UpdateBackpackGateway updateBackpackGateway;
    private UpdatePlayerGateway updatePlayerGateway;

    public DefaultUseItem(GetPlayerGateway getPlayerGateway,
                          UpdateBackpackGateway updateBackpackGateway,
                          UpdatePlayerGateway updatePlayerGateway) {
        this.getPlayerGateway = getPlayerGateway;
        this.updateBackpackGateway = updateBackpackGateway;
        this.updatePlayerGateway = updatePlayerGateway;
    }

    @Override
    public void useItem(RequestModel requestModel) throws BackpackIsFullException, ItemWillNotFitInBackpackException {

        final Player player = getPlayer(requestModel);
        final Backpack backpack = player.getBackpack();
        final Item item = getItem(backpack, requestModel);

        if (item instanceof HealthItem) {
            useHealthItem(player, (HealthItem) item);
        } else {
            useWeapon(player, (Weapon) item);
        }

        updateBackpackGateway.updateBackpack(backpack);
        updatePlayerGateway.updatePlayer(player);

    }

    private void useHealthItem(Player player, HealthItem healthItem) {

        Backpack backpack = player.getBackpack();
        backpack.dropItem(healthItem);
        player.heal(healthItem.getType().getHealthPower());
    }

    private void useWeapon(Player player, Weapon weapon) throws BackpackIsFullException, ItemWillNotFitInBackpackException {

        Backpack backpack = player.getBackpack();
        Weapon oldPlayerWeapon = player.getWeapon();

        backpack.dropItem(weapon);
        player.chooseWeapon(weapon);
        backpack.addItem(oldPlayerWeapon);
    }


    private Player getPlayer(RequestModel requestModel) {
        return getPlayerGateway.getById(requestModel.getPlayerId())
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    private Item getItem(Backpack backpack, RequestModel requestModel) {

        return backpack.getItems().stream()
                .filter(healthItem -> healthItem.getId() == requestModel.getItemId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Health Item not found"));

    }
}
