package pihead.games.rpg.commandline.config;

import pihead.games.rpg.commandline.console.Console;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

    private static Properties properties = new Properties();

    private static boolean started = false;

    public static void init() {

        InputStream inputStream = null;

        try {
            try {
                String propFileName = "config.properties";

                inputStream = (new ConfigProperties()).getClass().getClassLoader().getResourceAsStream(propFileName);

                if (inputStream != null) {
                    properties.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }

                started = true;

                // TODO: log
//            Date time = new Date(System.currentTimeMillis());
//            String result = "Company List = " + company1 + ", " + company2 + ", " + company3;
//            System.out.println(result + "\nProgram Ran on " + time + " by user=" + user);
            } catch (Exception e) {
//            System.out.println("Exception: " + e);
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } catch (Exception ex) {
            Console.println("some problem here!");
        }

    }

    public static String get(String key) {
        ensureStarted();
        return properties.getProperty(key);
    }

    private static void ensureStarted() {

        if (!started) {
            init();
        }
    }
}
