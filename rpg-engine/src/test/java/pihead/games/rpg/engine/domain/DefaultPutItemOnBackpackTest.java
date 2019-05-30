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
import pihead.games.rpg.engine.domain.entities.items.Item;
import pihead.games.rpg.engine.domain.entities.items.WeaponType;
import pihead.games.rpg.engine.gateway.GetItemGateway;
import pihead.games.rpg.engine.gateway.GetPlayerGateway;
import pihead.games.rpg.engine.gateway.UpdateBackpackGateway;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.*;
import static pihead.games.rpg.engine.domain.TestHelper.nextPositiveInt;
import static pihead.games.rpg.engine.domain.entities.EntitiesTestHelper.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultPutItemOnBackpackTest {

    private PutItemOnBackpack usecase;

    @Mock
    private GetPlayerGateway getPlayerGateway;
    @Mock
    private GetItemGateway getItemGateway;
    @Mock
    private UpdateBackpackGateway updateBackpackGateway;

    @Before
    public void setup() {
        usecase = new DefaultPutItemOnBackpack(getPlayerGateway, getItemGateway, updateBackpackGateway);
    }

    @Test
    public void healthItemAddedOnBackpack() throws BackpackIsFullException, ItemWillNotFitInBackpackException {

        // given
        Player player = gimmePlayer().build();
        Backpack backpack = player.getBackpack();
        Item item = gimmeHealthItem().build();
        int initialAvailableSlots = backpack.getAvailableSlots();
        int initialItemSize = backpack.getItems().size();

        when(getPlayerGateway.getById(player.getId())).thenReturn(Optional.of(player));
        when(getItemGateway.getById(item.getId())).thenReturn(Optional.of(item));

        // when
        usecase.putItem(player.getId(), item.getId());

        // then
        assertThat(backpack.getAvailableSlots()).isEqualTo(initialAvailableSlots - item.getType().getSlots());
        assertThat(backpack.getItems().size()).isEqualTo(initialItemSize + 1);
        verify(updateBackpackGateway, only()).updateBackpack(backpack);

    }

    @Test
    public void weaponAddedOnBackpack() throws BackpackIsFullException, ItemWillNotFitInBackpackException {

        // given
        Player player = gimmePlayer().build();
        Backpack backpack = player.getBackpack();
        Item item = gimmeWeapon().build();
        int initialAvailableSlots = backpack.getAvailableSlots();
        int initialItemSize = backpack.getItems().size();

        when(getPlayerGateway.getById(player.getId())).thenReturn(Optional.of(player));
        when(getItemGateway.getById(item.getId())).thenReturn(Optional.of(item));

        // when
        usecase.putItem(player.getId(), item.getId());

        // then
        assertThat(backpack.getAvailableSlots()).isEqualTo(initialAvailableSlots - item.getType().getSlots());
        assertThat(backpack.getItems().size()).isEqualTo(initialItemSize + 1);
        verify(updateBackpackGateway, only()).updateBackpack(backpack);

    }

    @Test
    public void weaponWithTwoSlotsAddedOnBackpack() throws BackpackIsFullException, ItemWillNotFitInBackpackException {

        // given
        Player player = gimmePlayer().build();
        Backpack backpack = player.getBackpack();
        WeaponType weaponType = gimmeWeaponType().slots(1).build();
        Item item = gimmeWeapon().type(weaponType).build();
        int initialAvailableSlots = backpack.getAvailableSlots();
        int initialItemSize = backpack.getItems().size();

        when(getPlayerGateway.getById(player.getId())).thenReturn(Optional.of(player));
        when(getItemGateway.getById(item.getId())).thenReturn(Optional.of(item));

        // when
        usecase.putItem(player.getId(), item.getId());

        // then
        assertThat(backpack.getAvailableSlots()).isEqualTo(initialAvailableSlots - item.getType().getSlots());
        assertThat(backpack.getItems().size()).isEqualTo(initialItemSize + 1);
        verify(updateBackpackGateway, only()).updateBackpack(backpack);

    }

    @Test
    public void playerNotFound() {

        // given
        int playerId = nextPositiveInt();
        int itemId = nextPositiveInt();

        when(getPlayerGateway.getById(playerId)).thenReturn(Optional.empty());

        // when
        Throwable thrown = catchThrowable(() ->
                usecase.putItem(playerId, itemId)
        );

        // then
        assertThat(thrown).isExactlyInstanceOf(RuntimeException.class)
                .hasMessage("Player not found");

    }
    @Test
    public void itemNotFound() {

        // given
        Player player = gimmePlayer().build();
        int itemId = nextPositiveInt();

        when(getPlayerGateway.getById(player.getId())).thenReturn(Optional.of(player));
        when(getItemGateway.getById(itemId)).thenReturn(Optional.empty());

        // when
        Throwable thrown = catchThrowable(() ->
                usecase.putItem(player.getId(), itemId)
        );

        // then
        assertThat(thrown).isExactlyInstanceOf(RuntimeException.class)
                .hasMessage("Item not found");

    }
}
