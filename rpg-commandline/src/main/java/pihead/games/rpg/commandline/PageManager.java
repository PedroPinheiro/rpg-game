package pihead.games.rpg.commandline;

import pihead.games.rpg.commandline.console.Console;
import pihead.games.rpg.commandline.context.ApplicationContext;
import pihead.games.rpg.commandline.responses.Intent;
import pihead.games.rpg.commandline.views.Page;

import java.util.EmptyStackException;
import java.util.Stack;

public class PageManager {

    private Stack<Intent> pageHistory = new Stack<>();

    public void showPage(Intent intent) {

        Page page = getPage(intent);

        pageHistory.add(intent);
        Console.clearScreen();
        Intent returnIntent = page.show();

        handlePageIntent(returnIntent);

    }

    private void handlePageIntent(Intent intent) {

        if (intent.getAction() != null) {
            runAction(intent.getAction());
        } else if (intent.getPageClazz() != null) {
            showPage(intent);
        }

    }

    private void runAction(Intent.Action action) {
        if (action == Intent.Action.GO_BACK) {
            goBack();
        }
        else if (action == Intent.Action.EXIT_GAME) {
            exitGame();
        }
    }

    private void goBack() {
        pageHistory.pop();
        try {
            Intent intent = pageHistory.peek();
            showPage(intent);
        } catch(EmptyStackException ex) {
            exitGame();
        }
    }

    private void exitGame() {
        System.exit(1);
    }

    private Page getPage(Intent intent) {

        Class<?> pageClazz = intent.getPageClazz();

        Page page = (Page) ApplicationContext.get(pageClazz);
        page.setModel(intent.getModel());

        return page;
    }
}
