package pihead.games.rpg.commandline.pages.selectGame;

import pihead.games.rpg.commandline.models.*;
import pihead.games.rpg.engine.domain.ListGameTypes;

import java.util.List;
import java.util.stream.Collectors;

public class SelectGamePresenter {

    private ListGameTypes listGameTypes;

    public SelectGamePresenter(ListGameTypes listGameTypes) {
        this.listGameTypes = listGameTypes;
    }

    public InitialModel getModel() {

        ListGameTypes.ResponseModel responseModel = listGameTypes.list();

        List<OptionModel> options = responseModel.getGameTypes().stream()
                .map(gt -> new GameTypeModel(gt.getId(), gt.getName()))
                .collect(Collectors.toList());

        return new InitialModel(options);

    }

}
