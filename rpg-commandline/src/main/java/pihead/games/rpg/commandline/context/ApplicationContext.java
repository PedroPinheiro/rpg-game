package pihead.games.rpg.commandline.context;

import pihead.games.rpg.commandline.GameManager;
import pihead.games.rpg.commandline.Main;
import pihead.games.rpg.commandline.PageManager;
import pihead.games.rpg.commandline.data.gateways.MemoryGameTypeGateway;
import pihead.games.rpg.commandline.data.gateways.MemoryPlayerTypeGateway;
import pihead.games.rpg.commandline.data.repositories.GameTypeRepository;
import pihead.games.rpg.commandline.data.repositories.PlayerTypeRepository;
import pihead.games.rpg.commandline.pages.playerName.PlayerNamePresenter;
import pihead.games.rpg.commandline.views.SelectOptionView;
import pihead.games.rpg.commandline.pages.playerName.PlayerNamePage;
import pihead.games.rpg.commandline.pages.selectPlayer.SelectPlayerPage;
import pihead.games.rpg.commandline.pages.selectGame.SelectGamePage;
import pihead.games.rpg.commandline.pages.selectPlayer.SelectPlayerPresenter;
import pihead.games.rpg.commandline.pages.selectGame.SelectGamePresenter;
import pihead.games.rpg.engine.domain.DefaultListGameTypes;
import pihead.games.rpg.engine.domain.DefaultListPlayerTypes;
import pihead.games.rpg.engine.domain.ListGameTypes;
import pihead.games.rpg.engine.domain.ListPlayerTypes;
import pihead.games.rpg.engine.gateway.GetGameTypeGateway;
import pihead.games.rpg.engine.gateway.GetPlayerTypeGateway;
import pihead.games.rpg.engine.gateway.ListGameTypeGateway;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private static Map<Class<?>, Object> beans = new HashMap<>();

    private static void registerConsoleGameLoader() {

        ConsoleGameLoader consoleGameLoader = new PropertiesConsoleGameLoader();
        put(ConsoleGameLoader.class, consoleGameLoader);

    }

    private static void registerRepositories() {

        PlayerTypeRepository playerTypeRepository = new PlayerTypeRepository();
        put(PlayerTypeRepository.class, playerTypeRepository);

        GameTypeRepository gameTypeRepository = new GameTypeRepository(get(PlayerTypeRepository.class));
        put(GameTypeRepository.class, gameTypeRepository);
    }

    private static void registerGateways() {

        MemoryPlayerTypeGateway memoryPlayerTypeGateway = new MemoryPlayerTypeGateway(get(PlayerTypeRepository.class));
        put(GetPlayerTypeGateway.class, memoryPlayerTypeGateway);

        MemoryGameTypeGateway memoryGameTypeGateway = new MemoryGameTypeGateway(get(GameTypeRepository.class));
        put(ListGameTypeGateway.class, memoryGameTypeGateway);
        put(GetGameTypeGateway.class, memoryGameTypeGateway);

    }

    private static void registerUsecases() {

        ListGameTypes listGameTypes = new DefaultListGameTypes(get(ListGameTypeGateway.class));
        put(ListGameTypes.class, listGameTypes);

        ListPlayerTypes listPlayerTypes = new DefaultListPlayerTypes(get(GetGameTypeGateway.class));
        put(ListPlayerTypes.class, listPlayerTypes);

    }

    private static void registerViews() {

        SelectOptionView selectOptionView = new SelectOptionView();
        put(SelectOptionView.class, selectOptionView);

    }

    private static void registerPresenters() {

        SelectPlayerPresenter selectPlayerPresenter = new SelectPlayerPresenter(get(ConsoleGameLoader.class), get(ListPlayerTypes.class));
        put(SelectPlayerPresenter.class, selectPlayerPresenter);

        SelectGamePresenter selectGamePresenter = new SelectGamePresenter(get(ListGameTypes.class));
        put(SelectGamePresenter.class, selectGamePresenter);

        PlayerNamePresenter playerNamePresenter = new PlayerNamePresenter();
        put(PlayerNamePresenter.class, playerNamePresenter);

    }

    private static void registerPages() {

        SelectGamePage selectGamePage = new SelectGamePage(get(SelectGamePresenter.class), get(SelectOptionView.class));
        put(SelectGamePage.class, selectGamePage);

        SelectPlayerPage selectPlayerPage = new SelectPlayerPage(get(SelectPlayerPresenter.class), get(SelectOptionView.class));
        put(SelectPlayerPage.class, selectPlayerPage);

        PlayerNamePage playerNamePage = new PlayerNamePage(get(PlayerNamePresenter.class));
        put(PlayerNamePage.class, playerNamePage);

    }

    private static void registerMain() {

        PageManager pageManager = new PageManager();
        put(PageManager.class, pageManager);

        GameManager gameManager = new GameManager(get(GameTypeRepository.class),
                get(ConsoleGameLoader.class),
                get(PageManager.class));

        put(GameManager.class, gameManager);

        Main main = new Main(get(GameManager.class));
        put(Main.class, main);

    }

    public static <T> void put(Class<T> clazz, T object) {
        beans.put(clazz, object);
    }

    public static <T> T get(Class<T> clazz) {
        return (T) beans.get(clazz);
    }


    public static Main init(Class<Main> mainClass) {

        registerConsoleGameLoader();
        registerRepositories();
        registerGateways();
        registerUsecases();
        registerViews();
        registerPresenters();
        registerPages();
        registerMain();

        return get(Main.class);
    }
}
