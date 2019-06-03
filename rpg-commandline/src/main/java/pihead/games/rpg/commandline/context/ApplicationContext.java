package pihead.games.rpg.commandline.context;

import pihead.games.rpg.commandline.GameManager;
import pihead.games.rpg.commandline.Main;
import pihead.games.rpg.commandline.data.gateways.MemoryGameTypeGateway;
import pihead.games.rpg.commandline.data.gateways.MemoryPlayerTypeGateway;
import pihead.games.rpg.commandline.data.repositories.GameTypeRepository;
import pihead.games.rpg.commandline.data.repositories.PlayerTypeRepository;
import pihead.games.rpg.commandline.views.SelectOptionView;
import pihead.games.rpg.commandline.views.pages.GameHomePage;
import pihead.games.rpg.commandline.views.pages.InitialPage;
import pihead.games.rpg.commandline.presenters.GamePresenter;
import pihead.games.rpg.commandline.presenters.InitialPresenter;
import pihead.games.rpg.engine.domain.DefaultListGameTypes;
import pihead.games.rpg.engine.domain.ListGameTypes;
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

    }

    private static void registerViews() {

        SelectOptionView selectOptionView = new SelectOptionView();
        put(SelectOptionView.class, selectOptionView);

    }

    private static void registerPages() {

        GameHomePage gameHomePage = new GameHomePage();
        put(GameHomePage.class, gameHomePage);

        InitialPage initialPage = new InitialPage(get(SelectOptionView.class));
        put(InitialPage.class, initialPage);

    }

    private static void registerPresenters() {

        GamePresenter gamePresenter = new GamePresenter(get(ConsoleGameLoader.class), get(GameHomePage.class));
        put(GamePresenter.class, gamePresenter);

        InitialPresenter initialPresenter = new InitialPresenter(get(ListGameTypes.class), get(InitialPage.class));
        put(InitialPresenter.class, initialPresenter);

    }

    private static void registerMain() {

        GameManager gameManager = new GameManager(get(GameTypeRepository.class),
                get(ConsoleGameLoader.class),
                get(InitialPresenter.class));

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
        registerPages();
        registerPresenters();
        registerMain();

        return get(Main.class);
    }
}
