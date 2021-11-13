package gcym.utils;

import org.apache.logging.log4j.Logger;

public class GTMALog {
    public static Logger logger;

    public GTMALog() {
    }

    public static void init(Logger modLogger) {
        logger = modLogger;
    }

}
