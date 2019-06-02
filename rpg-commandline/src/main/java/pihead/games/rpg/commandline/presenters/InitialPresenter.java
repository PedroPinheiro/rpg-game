package pihead.games.rpg.commandline.presenters;

import pihead.games.rpg.commandline.models.InitialModel;
import pihead.games.rpg.commandline.models.OptionModel;
import pihead.games.rpg.commandline.pages.InitialPage;
import pihead.games.rpg.engine.domain.ListGameTypes;

import java.util.List;
import java.util.stream.Collectors;

public class InitialPresenter implements Presenter<InitialPage, InitialModel> {

    private ListGameTypes listGameTypes;
    private InitialPage initialPage;

    public InitialPresenter(ListGameTypes listGameTypes,
                            InitialPage initialPage) {
        this.listGameTypes = listGameTypes;
        this.initialPage = initialPage;
    }

    public InitialPage getPage() {

        return initialPage;
    }

    public InitialModel getModel() {

        ListGameTypes.ResponseModel responseModel = listGameTypes.list();

        List<OptionModel> options = responseModel.getGameTypes().stream()
                .map(gt -> new OptionModel(gt.getId(), gt.getName()))
                .collect(Collectors.toList());

        return new InitialModel(options);

    }
}
