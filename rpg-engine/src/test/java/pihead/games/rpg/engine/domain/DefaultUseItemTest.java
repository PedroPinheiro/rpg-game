package pihead.games.rpg.engine.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pihead.games.rpg.engine.domain.entities.backpack.Backpack;
import pihead.games.rpg.engine.domain.entities.backpack.exception.BackpackIsFullException;
import pihead.games.rpg.engine.domain.entities.backpack.exception.ItemWillNotFitInBackpackException;
import pihead.games.rpg.engine.domain.entities.characters.Player;
import pihead.games.rpg.engine.domain.entities.items.HealthItem;
import pihead.games.rpg.engine.domain.entities.items.Item;
import pihead.games.rpg.engine.domain.entities.items.Weapon;
import pihead.games.rpg.engine.domain.entities.items.WeaponType;
import pihead.games.rpg.engine.gateway.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;
import static pihead.games.rpg.engine.domain.TestHelper.nextPositiveInt;
import static pihead.games.rpg.engine.domain.entities.EntitiesTestHelper.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUseItemTest {

    private UseItem usecase;

    @Mock
    private GetPlayerGateway getPlayerGateway;
    @Mock
    private UpdateBackpackGateway updateBackpackGateway;
    @Mock
    private UpdatePlayerGateway updatePlayerGateway;

    @Before
    public void setup() {
        usecase = new DefaultUseItem(getPlayerGateway, updateBackpackGateway, updatePlayerGateway);
    }

    @Test
    public void useHealthItem() throws BackpackIsFullException, ItemWillNotFitInBackpackException {

        // given
        int initialPlayerHealth = 50;
        Player player = gimmePlayer().health(initialPlayerHealth).build();
        Backpack backpack = player.getBackpack();
        HealthItem healthItem = gimmeHealthItem().build();
        backpack.addItem(healthItem);
        int initialAvailableSlots = backpack.getAvailableSlots();
        int initialItemSize = backpack.getItems().size();

        when(getPlayerGateway.getById(player.getId())).thenReturn(Optional.of(player));

        // when
        usecase.useItem(player.getId(), healthItem.getId());

        // then
        assertThat(backpack.getAvailableSlots()).isEqualTo(initialAvailableSlots + healthItem.getType().getSlots());
        assertThat(backpack.getItems().size()).isEqualTo(initialItemSize - 1);
        assertThat(player.getHealth()).isEqualTo(initialPlayerHealth + healthItem.getType().getHealthPower());

        verify(updateBackpackGateway, only()).updateBackpack(backpack);
        verify(updatePlayerGateway, only()).updatePlayer(player);

    }

    @Test
    public void useWeapon() throws BackpackIsFullException, ItemWillNotFitInBackpackException {

        // given : old weapon
        int oldPlayerWeaponSlots = 2;
        WeaponType oldPlayerWeaponType = gimmeWeaponType().slots(oldPlayerWeaponSlots).build();
        Weapon oldPlayerWeapon = gimmeWeapon().type(oldPlayerWeaponType).build();

        // given : player
        Player player = gimmePlayer().build();
        player.chooseWeapon(oldPlayerWeapon);
        Backpack backpack = player.getBackpack();

        // given : new weapon
        int newWeaponSlots = 1;
        WeaponType newWeaponType = gimmeWeaponType().slots(newWeaponSlots).build();
        Weapon weapon = gimmeWeapon().type(newWeaponType).build();
        backpack.addItem(weapon);

        int initialAvailableSlots = backpack.getAvailableSlots();
        int initialItemSize = backpack.getItems().size();

        when(getPlayerGateway.getById(player.getId())).thenReturn(Optional.of(player));

        // when
        usecase.useItem(player.getId(), weapon.getId());

        // then
        verify(updateBackpackGateway, only()).updateBackpack(backpack);
        verify(updatePlayerGateway, only()).updatePlayer(player);

        // old weapon is on backpack
        Optional<Item> oldWeaponFoundOnBackpack = backpack.getItems().stream()
                .filter(i -> i.getId() == oldPlayerWeapon.getId())
                .findFirst();
        assertThat(oldWeaponFoundOnBackpack.isPresent()).isTrue();

        // modification on the backpack
        assertThat(backpack.getItems().size()).isEqualTo(initialItemSize);
        assertThat(backpack.getAvailableSlots()).isEqualTo(initialAvailableSlots + newWeaponSlots - oldPlayerWeaponSlots);

        // Weapon taken from backpack now is on the weapon
        assertThat(player.getWeapon()).isEqualTo(weapon);

    }

    @Test
    public void playerNotFound() {

        // given
        int playerId = nextPositiveInt();
        int healthItemId = nextPositiveInt();

        when(getPlayerGateway.getById(playerId)).thenReturn(Optional.empty());

        // when
        Throwable thrown = catchThrowable(() ->
                usecase.useItem(playerId, healthItemId)
        );

        // then
        assertThat(thrown).isExactlyInstanceOf(RuntimeException.class)
                .hasMessage("Player not found");

        verify(updateBackpackGateway, never()).updateBackpack(any(Backpack.class));
        verify(updatePlayerGateway, never()).updatePlayer(any(Player.class));

    }

    @Test
    public void itemNotFound() {

        // given
        Player player = gimmePlayer().build();
        int healthItem = nextPositiveInt();

        when(getPlayerGateway.getById(player.getId())).thenReturn(Optional.of(player));

        // when
        Throwable thrown = catchThrowable(() ->
                usecase.useItem(player.getId(), healthItem)
        );

        // then
        assertThat(thrown).isExactlyInstanceOf(RuntimeException.class)
                .hasMessage("Health Item not found");

        verify(updateBackpackGateway, never()).updateBackpack(any(Backpack.class));
        verify(updatePlayerGateway, never()).updatePlayer(any(Player.class));

    }


}
