package pihead.games.rpg.commandline;

import pihead.games.rpg.commandline.config.ConfigProperties;
import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.context.ApplicationContext;
import pihead.games.rpg.commandline.context.ConsoleGameLoader;
import pihead.games.rpg.commandline.data.gateways.MemoryGameTypeGateway;
import pihead.games.rpg.commandline.data.repositories.GameTypeRepository;
import pihead.games.rpg.commandline.models.Model;
import pihead.games.rpg.commandline.views.pages.Page;
import pihead.games.rpg.commandline.responses.GameResponse;
import pihead.games.rpg.commandline.responses.GameResponsePage;
import pihead.games.rpg.commandline.presenters.InitialPresenter;
import pihead.games.rpg.commandline.presenters.Presenter;
import pihead.games.rpg.commandline.responses.GameResponseShutdown;
import pihead.games.rpg.engine.domain.entities.GameType;

import java.util.List;

public final class GameManager {

    private GameTypeRepository gameTypeRepository;
    private ConsoleGameLoader consoleGameLoader;
    private InitialPresenter initialPresenter;

    public GameManager(GameTypeRepository gameTypeRepository,
                       ConsoleGameLoader consoleGameLoader,
                       InitialPresenter initialPresenter) {
        this.gameTypeRepository = gameTypeRepository;
        this.consoleGameLoader = consoleGameLoader;
        this.initialPresenter = initialPresenter;
    }

    public void initGame() {

        Console.initConsole();

        try {

            initGameTypes();
            runPresenter(initialPresenter, null);

        } catch (Exception ex) {

            Console.println(TextColor.RED, ex.getClass().getName());
            for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
                Console.println(TextColor.RED, "\n" + stackTraceElement);
            }

        } finally {
            Console.initConsole();
        }

    }

    private void initGameTypes() {

        List<GameType> gameTypes = consoleGameLoader.getGameTypes();

        gameTypeRepository.addAll(gameTypes);
    }

    private void runPresenter(Presenter presenter, Model requestModel) {

        Page page = presenter.getPage();
        Model model = presenter.getModel(requestModel);
        GameResponse gameResponse = page.show(model);

        processResponse(gameResponse);
    }

    private void processResponse(GameResponse response) {

        if (response instanceof GameResponsePage) {

            GameResponsePage gameResponse = (GameResponsePage) response;

            Presenter presenter = (Presenter) ApplicationContext.get(gameResponse.getPresenterClazz());
            Model requestModel = gameResponse.getModel();

            runPresenter(presenter, requestModel);

        } else if (response instanceof GameResponseShutdown) {
            // TODO: log close
        }

    }
}
