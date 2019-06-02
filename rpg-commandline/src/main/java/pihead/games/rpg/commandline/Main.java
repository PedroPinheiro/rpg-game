package pihead.games.rpg.commandline;

import pihead.games.rpg.commandline.context.ApplicationContext;

public class Main {

    private GameManager gameManager;

    public Main(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    private void init() {
        gameManager.initGame();
    }

    public static void main(String[] args) {

        Main main = ApplicationContext.init(Main.class);
        main.init();

    }

}
