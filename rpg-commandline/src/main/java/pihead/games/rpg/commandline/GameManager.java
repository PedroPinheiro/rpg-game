package pihead.games.rpg.commandline;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.context.ApplicationContext;
import pihead.games.rpg.commandline.pages.InitialPage;
import pihead.games.rpg.commandline.pages.Page;
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

            runPresenter(initialPresenter);

        } catch (Exception ex) {

            Console.println(TextColor.RED, ex.getClass().getName());
            for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
                Console.println(TextColor.RED, "\n" + stackTraceElement);
            }

        } finally {
            Console.initConsole();
        }

    }

    protected void runPresenter(Presenter presenter) {

        Page page = presenter.getPage();
        GameResponse gameResponse = page.show(presenter.getModel());

        processResponse(gameResponse);
    }

    private void processResponse(GameResponse response) {

        if (response instanceof GameResponsePage) {

            GameResponsePage gameResponse = (GameResponsePage) response;

            Presenter presenter = (Presenter) ApplicationContext.get(gameResponse.getPresenterClazz());

            runPresenter(presenter);

        } else if (response instanceof GameResponseShutdown) {
            System.out.close();
        }

    }
}
