package pihead.games.rpg.engine.domain;

import java.util.Random;
import java.util.UUID;

public final class TestHelper {

    private static Random random = new Random();

    public static int nextPositiveInt() {
        return random.nextInt() & Integer.MAX_VALUE;
    }

    public static String nextString() {
        return UUID.randomUUID().toString();
    }
}
