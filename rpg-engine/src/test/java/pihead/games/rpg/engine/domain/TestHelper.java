package pihead.games.rpg.engine.domain;

import java.util.Random;

public final class TestHelper {

    private static Random random = new Random();

    public static int nextPositiveInt() {
        return random.nextInt() & Integer.MAX_VALUE;
    }
}
