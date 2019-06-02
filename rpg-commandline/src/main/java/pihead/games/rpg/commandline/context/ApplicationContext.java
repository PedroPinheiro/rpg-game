package pihead.games.rpg.commandline.context;

import pihead.games.rpg.commandline.GameManager;
import pihead.games.rpg.commandline.Main;
import pihead.games.rpg.commandline.data.gateways.MemoryGameTypeGateway;
import pihead.games.rpg.commandline.pages.GameHomePage;
import pihead.games.rpg.commandline.pages.InitialPage;
import pihead.games.rpg.commandline.presenters.GamePresenter;
import pihead.games.rpg.commandline.presenters.InitialPresenter;
import pihead.games.rpg.engine.domain.DefaultListGameTypes;
import pihead.games.rpg.engine.domain.ListGameTypes;
import pihead.games.rpg.engine.gateway.ListGameTypeGateway;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private static Map<Class<?>, Object> beans = new HashMap<>();

    private static void registerGateways() {

        put(ListGameTypeGateway.class, new MemoryGameTypeGateway());

    }

    private static void registerUsecases() {

        ListGameTypes listGameTypes = new DefaultListGameTypes(get(ListGameTypeGateway.class));
        put(ListGameTypes.class, listGameTypes);

    }

    private static void registerPages() {

        GameHomePage gameHomePage = new GameHomePage();
        put(GameHomePage.class, gameHomePage);

        InitialPage initialPage = new InitialPage();
        put(InitialPage.class, initialPage);

    }

    private static void registerPresenters() {

        GamePresenter gamePresenter = new GamePresenter(get(GameHomePage.class));
        put(GamePresenter.class, gamePresenter);

        InitialPresenter initialPresenter = new InitialPresenter(get(ListGameTypes.class), get(InitialPage.class));
        put(InitialPresenter.class, initialPresenter);

    }

    private static void registerMain() {

        GameManager gameManager = new GameManager(get(InitialPresenter.class));
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

        registerGateways();
        registerUsecases();
        registerPages();
        registerPresenters();
        registerMain();

        return get(Main.class);
    }
}
