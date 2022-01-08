package gregicality.multiblocks.api.utils;

import org.apache.logging.log4j.Logger;

public class GCYMLog {
    public static Logger logger;

    public GCYMLog() {
    }

    public static void init(Logger modLogger) {
        logger = modLogger;
    }

}
