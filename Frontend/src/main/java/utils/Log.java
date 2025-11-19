package utils;

import java.util.logging.Logger;

public class Log {
    private static final Logger LOGGER = Logger.getLogger(Log.class.getName());

    public static void log(String message) {
        LOGGER.info(message);
    }
}
