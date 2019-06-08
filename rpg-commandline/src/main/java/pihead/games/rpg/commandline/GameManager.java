package pihead.games.rpg.commandline;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.console.TextColor;
import pihead.games.rpg.commandline.context.ConsoleGameLoader;
import pihead.games.rpg.commandline.data.repositories.GameTypeRepository;
import pihead.games.rpg.commandline.responses.Intent;
import pihead.games.rpg.commandline.pages.selectGame.SelectGamePage;
import pihead.games.rpg.engine.domain.entities.GameType;

import java.util.List;

public final class GameManager {

    private GameTypeRepository gameTypeRepository;
    private ConsoleGameLoader consoleGameLoader;
    private PageManager pageManager;

    public GameManager(GameTypeRepository gameTypeRepository,
                       ConsoleGameLoader consoleGameLoader,
                       PageManager pageManager) {
        this.gameTypeRepository = gameTypeRepository;
        this.consoleGameLoader = consoleGameLoader;
        this.pageManager = pageManager;
    }

    public void initGame() {

        Console.initConsole();

        try {

            initGameTypes();
            runInitialPage();

        } catch (Exception ex) {

            Console.println(TextColor.RED, ex.getClass().getName());
            for (StackTraceElement stackTraceElement : ex.getStackTrace()) {
                Console.println(TextColor.RED, "\n" + stackTraceElement);
            }

            Console.readLine();

        } finally {
            Console.initConsole();
        }

    }

    private void initGameTypes() {

        List<GameType> gameTypes = consoleGameLoader.getGameTypes();
        gameTypeRepository.addAll(gameTypes);
    }

    private void runInitialPage() {

        Intent intent = new Intent(SelectGamePage.class);
        pageManager.showPage(intent);

        Console.readLine();

    }

}
