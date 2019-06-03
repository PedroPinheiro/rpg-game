package pihead.games.rpg.commandline.presenters;

import pihead.games.rpg.commandline.models.GameTypeModel;
import pihead.games.rpg.commandline.models.InitialModel;
import pihead.games.rpg.commandline.models.Model;
import pihead.games.rpg.commandline.models.OptionModel;
import pihead.games.rpg.commandline.pages.SelectGamePage;
import pihead.games.rpg.engine.domain.ListGameTypes;

import java.util.List;
import java.util.stream.Collectors;

public class InitialPresenter implements Presenter<SelectGamePage, InitialModel> {

    private ListGameTypes listGameTypes;
    private SelectGamePage selectGamePage;

    public InitialPresenter(ListGameTypes listGameTypes,
                            SelectGamePage selectGamePage) {
        this.listGameTypes = listGameTypes;
        this.selectGamePage = selectGamePage;
    }

    public SelectGamePage getPage() {

        return selectGamePage;
    }

    public InitialModel getModel(Model i) {

        ListGameTypes.ResponseModel responseModel = listGameTypes.list();

        List<OptionModel> options = responseModel.getGameTypes().stream()
                .map(gt -> new GameTypeModel(gt.getId(), gt.getName()))
                .collect(Collectors.toList());

        return new InitialModel(options);

    }

}
