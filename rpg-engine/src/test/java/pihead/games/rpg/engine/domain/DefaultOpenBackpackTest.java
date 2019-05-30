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
import pihead.games.rpg.engine.domain.OpenBackpack.ResponseModel;
import pihead.games.rpg.engine.domain.OpenBackpack.ResponseModel.*;
import pihead.games.rpg.engine.domain.entities.items.HealthItem;
import pihead.games.rpg.engine.domain.entities.items.Item;
import pihead.games.rpg.engine.domain.entities.items.Weapon;
import pihead.games.rpg.engine.gateway.GetPlayerGateway;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;
import static pihead.games.rpg.engine.domain.TestHelper.nextPositiveInt;
import static pihead.games.rpg.engine.domain.assertions.OpenBackpackResponseAssertion.assertThatOpenBackpackResponse;
import static pihead.games.rpg.engine.domain.entities.EntitiesTestHelper.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultOpenBackpackTest {

    private OpenBackpack usecase;

    @Mock
    private GetPlayerGateway getPlayerGateway;

    @Before
    public void setup() {
        usecase = new DefaultOpenBackpack(getPlayerGateway);
    }

    @Test
    public void emptyBackpack() {

        // given
        final Player player = gimmePlayer().build();
        final Backpack backpack = player.getBackpack();

        when(getPlayerGateway.getById(player.getId())).thenReturn(Optional.of(player));

        // when
        ResponseModel responseModel = usecase.openBackpack(player.getId());

        // then
        assertThat(responseModel.getBackpackId()).isEqualTo(backpack.getId());
        assertThat(responseModel.getAvailableSlots()).isEqualTo(backpack.getAvailableSlots());
        assertThat(responseModel.getMaxSlots()).isEqualTo(backpack.getMaxSlots());

        assertThatOpenBackpackResponse(responseModel).hasItemsOf(backpack);

    }

    @Test
    public void fullBackpack() throws BackpackIsFullException, ItemWillNotFitInBackpackException {

        // given
        final Player player = gimmePlayer().build();
        final Backpack backpack = player.getBackpack();
        backpack.addItem(gimmeHealthItem().build());
        backpack.addItem(gimmeHealthItem().build());
        backpack.addItem(gimmeWeapon().build());
        backpack.addItem(gimmeWeapon().build());

        when(getPlayerGateway.getById(player.getId())).thenReturn(Optional.of(player));

        // when
        ResponseModel responseModel = usecase.openBackpack(player.getId());

        // then
        assertThat(responseModel.getBackpackId()).isEqualTo(backpack.getId());
        assertThat(responseModel.getAvailableSlots()).isEqualTo(backpack.getAvailableSlots());
        assertThat(responseModel.getMaxSlots()).isEqualTo(backpack.getMaxSlots());

        assertThatOpenBackpackResponse(responseModel).hasItemsOf(backpack);

    }

    @Test
    public void playerNotFound() {

        // given
        final int playerId = nextPositiveInt();
        when(getPlayerGateway.getById(playerId)).thenReturn(Optional.empty());

        // when
        Throwable thrown = catchThrowable(() ->
                usecase.openBackpack(playerId)
        );

        // then
        assertThat(thrown).isExactlyInstanceOf(RuntimeException.class)
                .hasMessage("Player not found");

    }

}
