package pihead.games.rpg.engine.domain;

import pihead.games.rpg.engine.domain.entities.backpack.Backpack;
import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.domain.entities.items.HealthItem;
import pihead.games.rpg.engine.domain.entities.items.Item;
import pihead.games.rpg.engine.gateway.GetPlayerGateway;
import pihead.games.rpg.engine.gateway.UpdateBackpackGateway;
import pihead.games.rpg.engine.gateway.UpdatePlayerGateway;

public class DefaultUseHealthItem implements UseHealthItem {

    private GetPlayerGateway getPlayerGateway;
    private UpdateBackpackGateway updateBackpackGateway;
    private UpdatePlayerGateway updatePlayerGateway;

    public DefaultUseHealthItem(GetPlayerGateway getPlayerGateway,
                                UpdateBackpackGateway updateBackpackGateway,
                                UpdatePlayerGateway updatePlayerGateway) {
        this.getPlayerGateway = getPlayerGateway;
        this.updateBackpackGateway = updateBackpackGateway;
        this.updatePlayerGateway = updatePlayerGateway;
    }

    @Override
    public void useItem(RequestModel requestModel) {

        final Player player = getPlayer(requestModel);
        final Backpack backpack = player.getBackpack();
        final HealthItem healthItem = getHealthItem(backpack, requestModel);

        backpack.dropItem(healthItem);
        player.heal(healthItem.getType().getHealthPower());

        updateBackpackGateway.updateBackpack(backpack);
        updatePlayerGateway.updatePlayer(player);

    }


    private Player getPlayer(RequestModel requestModel) {
        return getPlayerGateway.getById(requestModel.getPlayerId())
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    private HealthItem getHealthItem(Backpack backpack, RequestModel requestModel) {

        Item item = backpack.getItems().stream()
                .filter(healthItem -> healthItem.getId() == requestModel.getHealthItemId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Health Item not found"));

        if (!(item instanceof HealthItem)) {
            throw new RuntimeException("Item is not a health item to heal");
        }

        return (HealthItem) item;
    }
}
