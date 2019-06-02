package pihead.games.rpg.commandline;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.context.ApplicationContext;
import pihead.games.rpg.commandline.models.Model;
import pihead.games.rpg.commandline.views.pages.Page;
import pihead.games.rpg.commandline.responses.GameResponse;
import pihead.games.rpg.commandline.responses.GameResponsePage;
import pihead.games.rpg.commandline.presenters.InitialPresenter;
import pihead.games.rpg.commandline.presenters.Presenter;
import pihead.games.rpg.commandline.responses.GameResponseShutdown;

public final class GameManager {

    private InitialPresenter initialPresenter;

    public GameManager(InitialPresenter initialPresenter) {
        this.initialPresenter = initialPresenter;
    }

    public void initGame() {

        Console.initConsole();

        try {

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
