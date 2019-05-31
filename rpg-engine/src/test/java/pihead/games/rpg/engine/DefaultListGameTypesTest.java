package pihead.games.rpg.engine;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pihead.games.rpg.engine.domain.DefaultListGameTypes;
import pihead.games.rpg.engine.domain.ListGameTypes;
import pihead.games.rpg.engine.domain.ListGameTypes.ResponseModel;
import pihead.games.rpg.engine.domain.ListGameTypes.ResponseModel.*;
import pihead.games.rpg.engine.domain.entities.GameType;
import pihead.games.rpg.engine.gateway.GetGameTypeGateway;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static pihead.games.rpg.engine.domain.entities.EntitiesTestHelper.gimmeGameType;

@RunWith(MockitoJUnitRunner.class)
public class DefaultListGameTypesTest {

    private ListGameTypes usecase;

    @Mock
    private GetGameTypeGateway getGameTypeGateway;

    @Before
    public void setup() {
        usecase = new DefaultListGameTypes(getGameTypeGateway);
    }

    @Test
    public void oneGame() {

        // given
        GameType gameType = gimmeGameType().build();
        List<GameType> gameTypes = new ArrayList<GameType>() {{ add(gameType); }};

        when(getGameTypeGateway.findAll()).thenReturn(gameTypes);

        // when
        ResponseModel list = usecase.list();

        // then
        assertThat(list.getGameTypes().size()).isEqualTo(gameTypes.size());
        assertThat(list.getGameTypes().get(0).getId()).isEqualTo(gameTypes.get(0).getId());
        assertThat(list.getGameTypes().get(0).getName()).isEqualTo(gameTypes.get(0).getName());
        assertThat(list.getGameTypes().get(0).getDescription()).isEqualTo(gameTypes.get(0).getDescription());

    }

    @Test
    public void threeGames() {

        // given
        GameType gameType = gimmeGameType().build();
        List<GameType> gameTypes = new ArrayList<GameType>() {{ add(gameType); }};

        when(getGameTypeGateway.findAll()).thenReturn(gameTypes);

        // when
        ResponseModel list = usecase.list();

        // then
        assertThat(list.getGameTypes().size()).isEqualTo(gameTypes.size());

        for (int i = 0; i< gameTypes.size(); i++) {
            assertThat(list.getGameTypes().get(i).getId()).isEqualTo(gameTypes.get(i).getId());
            assertThat(list.getGameTypes().get(i).getName()).isEqualTo(gameTypes.get(i).getName());
            assertThat(list.getGameTypes().get(i).getDescription()).isEqualTo(gameTypes.get(i).getDescription());
        }
    }

}
